package com.mazhj.felix.quartz;

import com.mazhj.common.core.utils.SpringUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author mazhj
 */
public class QuartzJobExecution extends QuartzJob{

    @Override
    protected void execute(Method method, Class<?> clazz) throws InvocationTargetException, IllegalAccessException {
        method.invoke(SpringUtil.getBean(clazz));
    }
}
