package com.mazhj.felix.book.common.event.listener;

import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.book.common.event.RefreshCache;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mazhj
 */
@Component
public class RefreshListener {

    private final RedisService redisService;

    public RefreshListener(RedisService redisService) {
        this.redisService = redisService;
    }

    @EventListener(classes = RefreshCache.class)
    public void refresh(RefreshCache refreshCache){
        List<String> cacheKeys = refreshCache.getCacheKeys();
        for (String cacheKey : cacheKeys) {
            this.redisService.remove(cacheKey);
        }
    }
}
