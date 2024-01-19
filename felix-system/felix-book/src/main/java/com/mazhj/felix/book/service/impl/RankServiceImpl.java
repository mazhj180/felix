package com.mazhj.felix.book.service.impl;

import com.mazhj.common.core.utils.Convert;
import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.book.mapper.BookMapper;
import com.mazhj.felix.book.pojo.model.Book;
import com.mazhj.felix.book.pojo.model.BookCategory;
import com.mazhj.felix.book.pojo.vo.BookVO;
import com.mazhj.felix.book.service.RankService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public List<BookVO> getHotRankings() {
        String key = KeyBuilder.Book.getHotRankingsKey();
        Set<String> hotRanking = this.redisService.rangeVal(key, 0L, 9L);
        List<Book> books = this.bookMapper.selectBatchByBookId(hotRanking.toArray(new String[0]));
        return toBookVoList(books);
    }

    @Override
    public List<BookVO> getLikeRankings() {
        int rank = 10;
        List<Book> books = this.bookMapper.select();
        List<BookVO> vos = toBookVoList(books);
        for (int i = 0; i < rank; i++) {
            BookVO temp;
            BookVO mostLike = vos.get(i);
            int maxIdx = i;
            for (int j = i + 1; j < vos.size(); j++) {
                if (vos.get(j).getSupportCount() > mostLike.getSupportCount()){
                    mostLike = vos.get(j);
                    maxIdx = j;
                }
            }
            if (maxIdx != i){
                temp = vos.get(maxIdx);
                vos.set(maxIdx,vos.get(i));
                vos.set(i,temp);
            }
        }
        return vos;
    }

    @Override
    public List<BookVO> getScoreRankings() {
        List<Book> books = this.bookMapper.selectBookSortedScore(10);
        return toBookVoList(books);
    }

    private List<BookVO> toBookVoList(List<Book> books){
        List<BookVO> vos = Convert.to(books,BookVO.class);
        for (BookVO vo : vos) {
            String bookId = vo.getBookId();
            String cKey = KeyBuilder.Book.getBookCategoriesKey(bookId);
            List<BookCategory> categories = this.redisService.get(cKey);
            if (categories.isEmpty()) {
                categories = this.bookMapper.selectCategoriesByBookId(bookId);
            }
            AtomicReference<List<com.mazhj.common.pojo.enums.BookCategory>> enums = new AtomicReference<>(new ArrayList<>());
            categories.forEach(category -> enums.get().add(category.getCategory()));
            vo.setCategories(enums.get());
        }
        return vos;
    }
}
