package com.mazhj.common.auth.aspect;

import com.mazhj.common.auth.anno.Auth;
import com.mazhj.common.auth.enums.AccountLevel;
import com.mazhj.common.core.exception.AuthException;
import com.mazhj.common.web.utils.ServletUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author mazhj
 */
@Aspect
public class AccountLevelAuthAspect {

    private static final String POINTCUT_SIGN = "@annotation(com.mazhj.common.auth.anno.Auth)";

    @Pointcut(POINTCUT_SIGN)
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        check(methodSignature.getMethod());
        return joinPoint.proceed();
    }

    private void check(Method method) throws AuthException {
        Auth auth = method.getAnnotation(Auth.class);
        if (auth != null){
            String level = ServletUtil.getRequest().getHeader("Account-Level");
            AccountLevel accountLevel = AccountLevel.valueOf(level);
            if (auth.value().getLevel() < accountLevel.getLevel()) {
                throw new AuthException("权限不足");
            }
        }
    }

}
