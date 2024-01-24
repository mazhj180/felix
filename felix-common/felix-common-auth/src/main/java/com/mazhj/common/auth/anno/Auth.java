package com.mazhj.common.auth.anno;

import com.mazhj.common.auth.enums.AccountLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mazhj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Auth {

    AccountLevel value() default AccountLevel.READER;

}
