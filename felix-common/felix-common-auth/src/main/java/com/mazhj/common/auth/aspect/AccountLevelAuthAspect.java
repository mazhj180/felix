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
import java.util.Optional;

/**
 * @author mazhj
 */
@Aspect
public class AccountLevelAuthAspect {

    private static final String POINTCUT_SIGN = "@within(com.mazhj.common.auth.anno.Auth) || @annotation(com.mazhj.common.auth.anno.Auth)";

    @Pointcut(POINTCUT_SIGN)
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Class<?> clazz = method.getDeclaringClass();
        Auth auth = Optional.ofNullable(clazz.getAnnotation(Auth.class)).orElse(method.getAnnotation(Auth.class));
        check(auth);
        return joinPoint.proceed();
    }

    private void check(Auth auth) throws AuthException {
        if (auth != null){
            String level = ServletUtil.getRequest().getHeader("Account-Level");
            AccountLevel accountLevel = AccountLevel.valueOf(level.toUpperCase());
            if (auth.value().getLevel() < accountLevel.getLevel()) {
                throw new AuthException("权限不足");
            }
        }
    }

}
