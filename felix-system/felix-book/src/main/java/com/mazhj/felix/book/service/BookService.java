package com.mazhj.felix.book.service;

import com.mazhj.felix.book.pojo.model.Book;

/**
 * @author mazhj
 */
public interface BookService {

    /**
     * 根据图书id获取图书信息
     * @param bookId 图书id
     * @return 图书model
     */
    Book getBookInfoByBookId(String bookId);

}
