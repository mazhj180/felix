package com.mazhj.felix.quartz.scanner;

import com.mazhj.common.core.constant.ScheduleConstants;
import com.mazhj.felix.quartz.QuartzDisallowConcurrentExecution;
import com.mazhj.felix.quartz.QuartzJobExecution;
import com.mazhj.felix.quartz.anno.Invoke;
import com.mazhj.felix.quartz.anno.QuartzTask;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

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
    public void run(ApplicationArguments args) throws Exception {
        this.scan();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    private void scan(){
        Map<String, Object> beans = context.getBeansWithAnnotation(QuartzTask.class);
        beans.forEach((key,value) -> Arrays.stream(value.getClass().getDeclaredMethods())
                .toList().forEach(method -> {
                    if (method.isAnnotationPresent(Invoke.class)){
                        Invoke annotation = method.getAnnotation(Invoke.class);
                        String name = value.getClass().getName() + method.getName();

                        Class target = annotation.isAllowConcurrent()?
                                        QuartzDisallowConcurrentExecution.class:
                                        QuartzJobExecution.class;
                        JobDetail jobDetail = JobBuilder.newJob(target)
                                .withIdentity(name).build();
                        Map<String, Object> param = new HashMap<>();
                        param.put("class",value.getClass());
                        param.put("method",method);
                        jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES,param);

                        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(annotation.cronExpression());
                        cronScheduleBuilder = switch (annotation.misfirePolicy()){
                            case MISFIRE_DO_NOTHING -> cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
                            case MISFIRE_IGNORE_MISFIRES -> cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
                            default -> cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
                        };

                        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name)
                                .withSchedule(cronScheduleBuilder).build();

                        try {
                            if (scheduler.checkExists(new JobKey(name))){
                                scheduler.deleteJob(new JobKey(name));
                            }

                            scheduler.scheduleJob(jobDetail,trigger);
                        } catch (SchedulerException e) {
                            log.error("定时任务 [{}]  异常: ",name,e);
                            throw new RuntimeException(e);
                        }
                    }
                }));

    }

    private void newJob(){

    }
}
