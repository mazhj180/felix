package com.mazhj.felix.search.service;

import com.mazhj.felix.search.pojo.Book;

import java.util.List;

/**
 * @author mazhj
 */
public interface SearchService {
    /**
     * 根据关键词搜索图书
     * @param keyword 关键词
     * @return 图书列表
     */
    List<Book> searchBooksByKeyword(String keyword);

    /**
     * 根据书名搜索图书
     * @param name 书名
     * @return 图书列表
     */
    List<Book> searchBooksByName(String name);

}
