package com.mazhj.felix.book.service;

import com.mazhj.felix.book.pojo.model.Book;
import com.mazhj.felix.book.pojo.model.BookCategory;
import com.mazhj.felix.book.pojo.model.Category;

import java.util.List;

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

    /**
     * 获取全部的图书
     * @return 图书列表
     */
    List<Book> getAllBook();

    /**
     * 获取图书分类信息
     * @param bookId 图书id
     * @return 分类信息
     */
    List<BookCategory> getCategoriesByBookId(String bookId);

}
