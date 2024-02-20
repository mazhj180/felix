package com.mazhj.felix.quartz;

import com.mazhj.common.core.utils.SpringUtil;
import org.quartz.DisallowConcurrentExecution;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author mazhj
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends QuartzJob{

    @Override
    protected void execute(Method method, Class<?> clazz) throws InvocationTargetException, IllegalAccessException {
        method.invoke(SpringUtil.getBean(clazz));
    }
}
