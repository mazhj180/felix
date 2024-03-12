package com.mazhj.common.quartz.scanner;

import com.mazhj.common.core.utils.UuidUtil;
import com.mazhj.common.quartz.anno.CronTask;
import com.mazhj.common.quartz.anno.Scheduled;
import com.mazhj.common.quartz.anno.Task;
import com.mazhj.common.quartz.constant.ScheduleConstants;
import com.mazhj.common.quartz.eums.MisfireInstruction;
import com.mazhj.common.quartz.trigger.TriggerProvider;
import com.mazhj.common.quartz.trigger.impl.NonTriggerProvider;
import com.mazhj.common.quartz.QuartzDisallowConcurrentExecution;
import com.mazhj.common.quartz.QuartzJobExecution;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mazhj
 */
@Slf4j
@Component
public class QuartzScanner implements ApplicationRunner, ApplicationContextAware {

    private ApplicationContext context;

    private final Scheduler scheduler;

    @Autowired
    public QuartzScanner(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run(ApplicationArguments args) {
        this.scan();
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    private void scan(){
        Map<String, Object> beans = context.getBeansWithAnnotation(Scheduled.class);
        beans.forEach(
                (key,value) -> Arrays.stream(value.getClass().getDeclaredMethods())
                .forEach(this::newJob)
        );
    }

    private void newJob(Method method) {
        try {
            if (method.isAnnotationPresent(Task.class)){
                Task taskAnno = method.getAnnotation(Task.class);
                JobDetail jobDetail = generateJobDetail(taskAnno);
                jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES,
                        new HashMap<String,Object>(2){{
                            put("class",method.getDeclaringClass());
                            put("method",method);
                        }});
                Class<? extends TriggerProvider> triggerClazz = taskAnno.triggerProvider();
                if (triggerClazz == NonTriggerProvider.class) {
                    throw new Exception("未绑定触发器");
                }else {
                    Constructor<? extends TriggerProvider> constructor = triggerClazz.getConstructor();
                    TriggerProvider triggerProvider = constructor.newInstance();
                    Trigger[] triggers = triggerProvider.declare();
                    for (Trigger trigger : triggers) {
                        scheduler.scheduleJob(jobDetail,trigger);
                    }
                }
            } else if (method.isAnnotationPresent(CronTask.class)){
                CronTask cronTask = method.getAnnotation(CronTask.class);
                Task task = cronTask.annotationType().getAnnotation(Task.class);
                JobDetail jobDetail = generateJobDetail(task);
                jobDetail.getJobDataMap().putAll(
                        new HashMap<String,Object>(2){{
                            put("class",method.getDeclaringClass());
                            put("method",method);
                        }});
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronTask.cronExpression());
                cronScheduleBuilder = switch (cronTask.misfirePolicy()){
                    case MISFIRE_DO_NOTHING -> cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
                    case MISFIRE_IGNORE_MISFIRES -> cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
                    default -> cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
                };

                CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(task.name() + UuidUtil.generateUuid())
                        .withSchedule(cronScheduleBuilder).build();
                scheduler.scheduleJob(jobDetail,trigger);
            }
        } catch (Exception e) {
            log.error("定时任务调度失败 cause：{}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private JobDetail generateJobDetail(Task taskAnno){

        Class<? extends Job> taskClazz = taskAnno.isAllowConcurrent()?
                QuartzDisallowConcurrentExecution.class:
                QuartzJobExecution.class;
        String taskName = taskAnno
                .name()
                .equals(ScheduleConstants.DEFAULT_TASK_NAME)
                ?UuidUtil.generateUuid():taskAnno.name();
        return  JobBuilder
                .newJob(taskClazz)
                .withIdentity(taskName, taskAnno.group())
                .build();
    }
}
