package com.mazhj.felix.forum.websocket.container;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mazhj.felix.forum.common.config.CaffeineCacheProperties;
import io.netty.channel.Channel;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author mazhj
 */
@Component
@EnableConfigurationProperties(value = {CaffeineCacheProperties.class})
public class CaffeineChannelContainer implements CacheContainer<String,Channel> {

    private final Cache<String, Channel> CONTAINER;


    public CaffeineChannelContainer(CaffeineCacheProperties properties) {
        CONTAINER = Caffeine.newBuilder()
                .initialCapacity(properties.channel.getInitialCapacity())
                .maximumSize(properties.channel.getMaximumSize())
                .build();
    }

    @Override
    public void put(String key, Channel val) {
        CONTAINER.put(key,val);
    }

    @Override
    public Channel get(String key) {
        return CONTAINER.getIfPresent(key);
    }

    @Override
    public void del(String key) {
        CONTAINER.invalidate(key);
    }
}
