package com.mazhj.common.quartz;

import com.mazhj.common.quartz.constant.ScheduleConstants;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author mazhj
 */
@Slf4j
public abstract class QuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            JobDetail jobDetail = jobExecutionContext.getJobDetail();
            Trigger trigger = jobExecutionContext.getTrigger();
            Class<?> clazz = (Class<?>) jobDetail.getJobDataMap().get("class");
            Method method = (Method) jobDetail.getJobDataMap().get("method");
            execute(method,clazz);
            log.info("定时任务 [{}] 执行 , 触发器 [{}] , 当前线程 [{}]",
                    jobDetail.getKey().getName(),trigger.getKey().getName(),Thread.currentThread().getName());
        } catch (InvocationTargetException | IllegalAccessException  e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 任务执行逻辑
     * @param method 执行的方法
     * @param clazz 任务的class
     * @throws InvocationTargetException InvocationTargetException
     * @throws IllegalAccessException IllegalAccessException
     */
    protected abstract void execute(Method method, Class<?> clazz) throws InvocationTargetException, IllegalAccessException;
}
