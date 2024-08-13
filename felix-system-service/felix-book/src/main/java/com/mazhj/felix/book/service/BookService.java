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
     * 批量获取图书
     * @param bookIds 图书ids
     * @return 图书们
     */
    List<Book> getBookInfoBatch(String[] bookIds);

    /**
     * 按评分排名获取图书
     * @param limit 限制条数
     * @return 图书列表
     */
    List<Book> getBookSortedScore(Integer limit);

    /**
     * 安创建时间排名
     * @param limit 限制条数
     * @return 图书列表
     */
    List<Book> getBookSortedTime(Integer limit);

    /**
     * 获取图书分类信息
     * @param bookId 图书id
     * @return 分类信息
     */
    List<BookCategory> getCategoriesByBookId(String bookId);

    /**
     * 图书上架
     * @param books 图书列表
     */
    void listingBook(List<Book> books);

    /**
     * 删除图书
     * @param bookId 图书id
     */
    void delBook(String bookId);

    void operate(Operator operator,String bookId,Integer score);

    /**
     * 根据分类获取图书信息
     * @param categoryId 分类id
     * @return 图书信息
     */
    List<Book> getBookByCategory(String categoryId);

    enum Operator {
        SCORE,
        SUPPORTS
    }

}
