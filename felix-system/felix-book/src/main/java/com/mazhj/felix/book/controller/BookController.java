package com.mazhj.felix.book.controller;

import com.mazhj.common.auth.anno.Auth;
import com.mazhj.common.auth.enums.AccountLevel;
import com.mazhj.common.core.utils.Convert;
import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.book.pojo.model.Book;
import com.mazhj.felix.book.pojo.model.BookCategory;
import com.mazhj.felix.book.pojo.vo.BookVO;
import com.mazhj.felix.book.service.BookService;
import com.mazhj.felix.book.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    @Auth(AccountLevel.ADMINISTRATORS)
    @PostMapping("/listing")
    public AjaxResult listing(MultipartFile file){

        return null;
    }

    @Auth(AccountLevel.WRITER)
    @PostMapping("/upload")
    public AjaxResult upload(){
        return null;
    }

    @GetMapping("/get/book-detail-info")
    public AjaxResult getBookDetailInfo(String bookId){
        Book book = this.bookService.getBookInfoByBookId(bookId);
        BookVO vo = Convert.to(book, BookVO.class);
        List<BookCategory> categories = this.bookService.getCategoriesByBookId(bookId);
        List<com.mazhj.common.pojo.enums.BookCategory> enums = new ArrayList<>();
        categories.forEach(category -> {
            enums.add(category.getCategory());
        });
        vo.setCategories(enums);
        return success(vo);
    }


    @GetMapping("/feign/get-book")
    public BookDTO getBookInfo(String bookId){
        Book book = this.bookService.getBookInfoByBookId(bookId);
        BookDTO bookDTO = Convert.to(book, BookDTO.class);
        List<BookCategory> categories = this.bookService.getCategoriesByBookId(bookId);

        List<com.mazhj.common.pojo.enums.BookCategory> enums = new ArrayList<>();
        categories.forEach(category -> {
            enums.add(category.getCategory());
        });
        bookDTO.setCategories(enums);
        return bookDTO;
    }

    @GetMapping("/feign/get-book-all")
    public List<BookDTO> getBookList(){
        List<Book> books = this.bookService.getAllBook();
        List<BookDTO> bookDTOS = Convert.to(books, BookDTO.class);
        for (int i = 0; i < books.size(); i++) {
            List<BookCategory> categories = this.bookService.getCategoriesByBookId(books.get(i).getBookId());
            AtomicReference<List<com.mazhj.common.pojo.enums.BookCategory>> enums = new AtomicReference<>(new ArrayList<>());
            categories.forEach(category -> {
                enums.get().add(category.getCategory());
            });
            bookDTOS.get(i).setCategories(enums.get());
        }
        return bookDTOS;
    }

    @GetMapping("/feign/get-book-batch")
    public List<BookDTO> getBatchBooks(String[] bookIds){
        List<Book> books = this.bookService.getBookInfoBatch(bookIds);
        List<BookDTO> bookDTOS = Convert.to(books, BookDTO.class);
        for (int i = 0; i < books.size(); i++) {
            List<BookCategory> categories = this.bookService.getCategoriesByBookId(books.get(i).getBookId());
            AtomicReference<List<com.mazhj.common.pojo.enums.BookCategory>> enums = new AtomicReference<>(new ArrayList<>());
            categories.forEach(category -> {
                enums.get().add(category.getCategory());
            });
            bookDTOS.get(i).setCategories(enums.get());
        }
        return bookDTOS;
    }

    @GetMapping("/feign/get-book-ss")
    public List<BookDTO> getBookSortedScore(Integer limit){
        List<Book> books = this.bookService.getBookSortedScore(limit);
        List<BookDTO> bookDTOS = Convert.to(books, BookDTO.class);
        for (int i = 0; i < books.size(); i++) {
            List<BookCategory> categories = this.bookService.getCategoriesByBookId(books.get(i).getBookId());
            AtomicReference<List<com.mazhj.common.pojo.enums.BookCategory>> enums = new AtomicReference<>(new ArrayList<>());
            categories.forEach(category -> {
                enums.get().add(category.getCategory());
            });
            bookDTOS.get(i).setCategories(enums.get());
        }
        return bookDTOS;
    }

}
