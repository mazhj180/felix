package com.mazhj.felix.homepage.service.impl;

import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.feign.book.clients.BookClient;
import com.mazhj.felix.homepage.service.RankService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mazhj
 */
@Service
public class RankServiceImpl implements RankService {

    private final RedisService redisService;

    private final BookClient bookClient;

    public RankServiceImpl(RedisService redisService, BookClient bookClient) {
        this.redisService = redisService;
        this.bookClient = bookClient;
    }

    @Override
    public List<BookDTO> getHotRankings() {
        String key = KeyBuilder.Homepage.getHotRankingsKey();
        return null;
    }

    @Override
    public List<BookDTO> getLikeRankings() {
        return null;
    }
}
