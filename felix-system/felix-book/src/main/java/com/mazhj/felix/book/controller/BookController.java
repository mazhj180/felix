package com.mazhj.felix.book.controller;

import com.mazhj.common.core.utils.Convert;
import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.common.web.controller.BaseController;
import com.mazhj.felix.book.pojo.model.Book;
import com.mazhj.felix.book.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mazhj
 */
@RestController
@RequestMapping("/book")
public class BookController extends BaseController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/feign/get-book")
    public BookDTO getBookInfo(String bookId){
        Book book = this.bookService.getBookInfoByBookId(bookId);
        return Convert.to(book, BookDTO.class);
    }

}
