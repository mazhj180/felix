package com.mazhj.felix.quartz.anno;

import com.mazhj.common.core.constant.ScheduleConstants;
import com.mazhj.felix.quartz.eums.MisfireInstruction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mazhj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Invoke {


    boolean isAllowConcurrent() default false;

    MisfireInstruction misfirePolicy() default MisfireInstruction.MISFIRE_FIRE_AND_PROCEED;

    String cronExpression() default ScheduleConstants.DEFAULT_CRON;

}
