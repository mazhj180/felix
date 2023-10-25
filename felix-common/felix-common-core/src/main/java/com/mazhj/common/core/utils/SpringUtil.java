package com.mazhj.common.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author mazhj
 */
@Component
public final class SpringUtil implements BeanFactoryPostProcessor {

    private static BeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtil.beanFactory = beanFactory;
    }

    public static <T> T getBean(Class<T> clazz){
        return beanFactory.getBean(clazz);
    }

    public static Object getBean(String beanName){
        return beanFactory.getBean(beanName);
    }
}
