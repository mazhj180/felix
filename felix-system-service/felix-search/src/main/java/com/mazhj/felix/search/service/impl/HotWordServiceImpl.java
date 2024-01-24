package com.mazhj.felix.search.service.impl;

import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.search.mapper.HotWordMapper;
import com.mazhj.felix.search.service.HotWordService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author mazhj
 */
@Service
public class HotWordServiceImpl implements HotWordService {

    private final HotWordMapper hotWordMapper;

    private final RedisService redisService;

    public HotWordServiceImpl(
            HotWordMapper hotWordMapper,
            RedisService redisService
            ) {
        this.hotWordMapper = hotWordMapper;
        this.redisService = redisService;
    }

    @Override
    public Set<String> getCurrentHotWord() {
        String hotWordKey = KeyBuilder.Search.getHotWordKey();
        return this.redisService.rangeVal(hotWordKey, 1L, 10L);
    }


}
