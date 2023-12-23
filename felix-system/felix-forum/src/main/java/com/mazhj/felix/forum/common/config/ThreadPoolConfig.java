package com.mazhj.felix.forum.common.config;

import com.mazhj.felix.forum.common.config.properties.ThreadPoolProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author mazhj
 */
@EnableAsync
@Configuration
@EnableConfigurationProperties(value = {ThreadPoolProperties.class})
public class ThreadPoolConfig {

    @Bean(value = "msgPushThreadPool")
    public Executor msgPushThreadPool(ThreadPoolProperties properties){
        ThreadPoolProperties.MsgPushThreadPool msgPushThreadPool = properties.getMsgPushThreadPool();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(msgPushThreadPool.getCoreThreadSize());
        executor.setMaxPoolSize(msgPushThreadPool.getMaximumThreadSize());
        executor.setQueueCapacity(msgPushThreadPool.getQueueCapacity());
        executor.setThreadNamePrefix(msgPushThreadPool.getThreadNamePrefix());
        executor.initialize();
        return executor;
    }

    @Bean(value = "saveDbThreadPool")
    public Executor saveDbThreadPool(ThreadPoolProperties properties){
        ThreadPoolProperties.SaveDbThreadPool saveDbThreadPool = properties.getSaveDbThreadPool();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(saveDbThreadPool.getCoreThreadSize());
        executor.setMaxPoolSize(saveDbThreadPool.getMaximumThreadSize());
        executor.setQueueCapacity(saveDbThreadPool.getQueueCapacity());
        executor.setThreadNamePrefix(saveDbThreadPool.getThreadNamePrefix());
        executor.initialize();
        return executor;
    }


}
