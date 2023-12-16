package com.mazhj.felix.forum.websocket.container;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mazhj.felix.forum.common.config.CaffeineCacheProperties;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mazhj
 */
@Component
public class CaffeineTopicContainer implements CacheContainer<String, List<String>>{

    private final Cache<String, List<String>> CONTAINER;

    public CaffeineTopicContainer(CaffeineCacheProperties properties) {
        CONTAINER = Caffeine.newBuilder()
                .initialCapacity(properties.topic.getInitialCapacity())
                .maximumSize(properties.topic.getMaximumSize())
                .build();
    }

    @Override
    public void put(String key, List<String> val) {
        CONTAINER.put(key,val);
    }

    @Override
    public List<String> get(String key) {
        return CONTAINER.getIfPresent(key);
    }

    @Override
    public void del(String key) {
        CONTAINER.invalidate(key);
    }

    public void remove(String topicId,String userId) {
        get(topicId).removeIf(val -> val.equals(userId));
    }

    public void add(String topicId,String userId) {
        get(topicId).add(userId);
    }
}
