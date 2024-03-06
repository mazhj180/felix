package com.mazhj.felix.book.service.impl;

import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.book.mapper.BookMapper;
import com.mazhj.felix.book.pojo.model.Book;
import com.mazhj.felix.book.pojo.model.BookCategory;
import com.mazhj.felix.book.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mazhj
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

    private final RedisService redisService;

    public BookServiceImpl(
            BookMapper bookMapper,
            RedisService redisService
            ) {
        this.bookMapper = bookMapper;
        this.redisService = redisService;
    }

    @Override
    public Book getBookInfoByBookId(String bookId) {
        String key = KeyBuilder.Book.getBookDetailKey(bookId);
        Book book = this.redisService.get(key);
        if (book == null){
            book = this.bookMapper.selectOneByBookId(bookId);
            this.redisService.set(key,book);
        }
        return book;
    }

    @Override
    public List<Book> getAllBook() {
        return this.bookMapper.select();
    }

    @Override
    public List<Book> getBookInfoBatch(String[] bookIds) {
        return this.bookMapper.selectBatchByBookId(bookIds);
    }

    @Override
    public List<Book> getBookSortedScore(Integer limit) {
        return this.bookMapper.selectBookSortedScore(limit);
    }

    @Override
    public List<BookCategory> getCategoriesByBookId(String bookId) {
        String key = KeyBuilder.Book.getBookCategoriesKey(bookId);
        List<BookCategory> categories = this.redisService.get(key);
        if (categories != null &&!categories.isEmpty()){
            return categories;
        }
        categories = this.bookMapper.selectCategoriesByBookId(bookId);
        this.redisService.set(key,categories);
        return categories;
    }

    @Override
    public void listingBook(List<Book> books) {

    }

    @Override
    public List<Book> getBookByCategory(String categoryId) {
        List<BookCategory> bookCategories = this.bookMapper.selectBookByCategory(categoryId);
        String[] bookIds = bookCategories.stream()
                .map(BookCategory::getBookId)
                .toArray(String[]::new);
        if (bookIds.length == 0){
            return new ArrayList<>();
        }
        return this.bookMapper.selectBatchByBookId(bookIds);
    }
}
