package com.mazhj.felix.search.controller;

import com.mazhj.common.core.utils.Convert;
import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.search.pojo.Book;
import com.mazhj.felix.search.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mazhj
 */
@RestController
@RequestMapping("search")
public class SearchController extends BaseController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/get-books/keyword")
    public AjaxResult getBooksByKeyword(String keyword){
        List<Book> bookList = this.searchService.searchBooksByKeyword(keyword);
        return success(Convert.to(bookList, BookDTO.class));
    }

    @GetMapping("/get-books/name")
    public AjaxResult getBooksByName(String name){
        List<Book> bookList = this.searchService.searchBooksByName(name);
        return success(Convert.to(bookList, BookDTO.class));
    }
}
