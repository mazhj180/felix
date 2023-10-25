package com.mazhj.felix.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author mazhj
 */
public abstract class QuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Class<?> clazz = (Class<?>) jobExecutionContext.getJobDetail().getJobDataMap().get("class");
            Method method = (Method) jobExecutionContext.getJobDetail().getJobDataMap().get("method");
            execute(method,clazz);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 任务执行逻辑
     * @param method 执行的方法
     * @param clazz 任务的class
     */
    protected abstract void execute(Method method, Class<?> clazz) throws InvocationTargetException, IllegalAccessException;
}
