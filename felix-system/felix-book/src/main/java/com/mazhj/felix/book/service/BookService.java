package com.mazhj.felix.book.service;

import com.mazhj.felix.book.pojo.model.Book;
import com.mazhj.felix.book.pojo.model.vo.BookVO;

/**
 * @author mazhj
 */
public interface BookService {

    Book getBookInfoByBookId(String bookId);

}
