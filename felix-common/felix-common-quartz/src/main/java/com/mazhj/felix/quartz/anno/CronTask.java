package com.mazhj.felix.quartz.anno;

import com.mazhj.felix.quartz.constant.ScheduleConstants;
import com.mazhj.felix.quartz.eums.MisfireInstruction;
import com.mazhj.felix.quartz.trigger.impl.NonTriggerProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mazhj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Task(name = ScheduleConstants.DEFAULT_TASK_NAME,triggerProvider = NonTriggerProvider.class)
public @interface CronTask {

    MisfireInstruction misfirePolicy() default MisfireInstruction.MISFIRE_FIRE_AND_PROCEED;

    String cronExpression() default ScheduleConstants.DEFAULT_CRON;

}
