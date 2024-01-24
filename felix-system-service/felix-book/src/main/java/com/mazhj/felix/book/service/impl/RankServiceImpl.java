package com.mazhj.felix.book.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mazhj.common.core.utils.Convert;
import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.book.mapper.BookMapper;
import com.mazhj.felix.book.pojo.model.Book;
import com.mazhj.felix.book.pojo.model.BookCategory;
import com.mazhj.felix.book.pojo.vo.BookVO;
import com.mazhj.felix.book.pojo.vo.RankVO;
import com.mazhj.felix.book.service.RankService;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author mazhj
 */
@Service
public class RankServiceImpl implements RankService {

    private final RedisService redisService;

    private final BookMapper bookMapper;

    public RankServiceImpl(RedisService redisService, BookMapper bookMapper) {
        this.redisService = redisService;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<RankVO> getHotRankings() {
        String key = KeyBuilder.Book.getHotRankingsKey();
        List<RankVO> hotRank = new ArrayList<>();
        Set<DefaultTypedTuple<JSONObject>> hotRankWithScore = this.redisService.rangeValWithScore(key, 0L, 9L);
        for (DefaultTypedTuple<JSONObject> typedTuple : hotRankWithScore) {
            Double hot = typedTuple.getScore();
            RankVO vo = Objects.requireNonNull(typedTuple.getValue()).to(RankVO.class);
            if (vo != null) {
                vo.setHot(hot);
            }
            hotRank.add(vo);
        }
        return hotRank;
    }

    @Override
    public List<RankVO> getLikeRankings() {
        int rank = 10;
        List<Book> books = this.bookMapper.selectBookSortedSupport(rank);
        return Convert.to(books, RankVO.class);
    }

    @Override
    public List<RankVO> getScoreRankings() {
        List<Book> books = this.bookMapper.selectBookSortedScore(10);
        return Convert.to(books,RankVO.class);
    }

    private List<BookVO> toBookVoList(List<Book> books){
        List<BookVO> vos = Convert.to(books,BookVO.class);
        for (BookVO vo : vos) {
            String bookId = vo.getBookId();
            String cKey = KeyBuilder.Book.getBookCategoriesKey(bookId);
            List<BookCategory> categories = this.redisService.get(cKey);
            if (categories == null) {
                categories = this.bookMapper.selectCategoriesByBookId(bookId);
            }
            AtomicReference<List<com.mazhj.common.pojo.enums.BookCategory>> enums = new AtomicReference<>(new ArrayList<>());
            categories.forEach(category -> enums.get().add(category.getCategory()));
            vo.setCategories(enums.get());
        }
        return vos;
    }
}
