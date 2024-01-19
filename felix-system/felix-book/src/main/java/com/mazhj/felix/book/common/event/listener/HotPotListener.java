package com.mazhj.felix.book.common.event.listener;

import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.book.common.event.ClickEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author mazhj
 */
@Component
public class HotPotListener {

    private final RedisService redisService;

    public HotPotListener(RedisService redisService) {
        this.redisService = redisService;
    }


    @EventListener(classes = ClickEvent.class)
    public void record(ClickEvent event){
        String bookId = event.getBookId();
        double hot = switch (event.getClickType()){
            case READ -> 5;
            case LOOK_THROUGH -> 2;
        };
        String key = KeyBuilder.Book.getHotRankingsKey();
        if (!this.redisService.hasKey(key)){
            this.redisService.addForZSet(key,bookId,0);
        }
        this.redisService.incrScore(key,bookId,hot);
    }
}
