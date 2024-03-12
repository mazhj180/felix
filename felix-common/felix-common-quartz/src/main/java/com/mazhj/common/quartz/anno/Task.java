package com.mazhj.common.quartz.anno;

import com.mazhj.common.quartz.trigger.TriggerProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mazhj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
public @interface Task {

    String group() default "default";

    String name();

    boolean isAllowConcurrent() default false;

    Class<? extends TriggerProvider> triggerProvider();
}
