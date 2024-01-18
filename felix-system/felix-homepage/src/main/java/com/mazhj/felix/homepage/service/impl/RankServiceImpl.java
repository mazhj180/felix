package com.mazhj.felix.homepage.service.impl;

import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.feign.book.clients.BookClient;
import com.mazhj.felix.homepage.service.RankService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
        Set<String> hotRanking = this.redisService.rangeVal(key, 0L, 9L);
        return this.bookClient.getBookBatch(hotRanking.toArray(new String[0]));
    }

    @Override
    public List<BookDTO> getLikeRankings() {
        int rank = 10;
        List<BookDTO> books = this.bookClient.getBookList();
        for (int i = 0; i < rank; i++) {
            BookDTO temp;
            BookDTO mostLike = books.get(i);
            int maxIdx = i;
            for (int j = i + 1; j < books.size(); j++) {
                if (books.get(j).getSupportCount() > mostLike.getSupportCount()){
                    mostLike = books.get(j);
                    maxIdx = j;
                }
            }
            if (maxIdx != i){
                temp = books.get(maxIdx);
                books.set(maxIdx,books.get(i));
                books.set(i,temp);
            }
        }
        return books;
    }

    @Override
    public List<BookDTO> getScoreRankings() {
        return this.bookClient.getBookSortedScore(10);
    }
}
