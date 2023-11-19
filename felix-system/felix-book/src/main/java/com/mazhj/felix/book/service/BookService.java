package com.mazhj.felix.book.service;

import com.mazhj.felix.book.pojo.model.Book;

/**
 * @author mazhj
 */
public interface BookService {

    Book getBookInfoByBookId(String bookId);

}
