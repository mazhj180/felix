package com.mazhj.felix.search.controller;

import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.search.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mazhj
 */
@RestController
public class SearchController extends BaseController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/{key}")
    public AjaxResult search(@PathVariable String key) {
        List<BookDTO> books = this.searchService.search(key);
        return success(books);
    }

    @GetMapping("/get-books/keyword")
    public AjaxResult getBooksByKeyword(@RequestParam(required = false) String keyword){
        List<BookDTO> bookList = this.searchService.searchBooksByKeyword(keyword);
        return success(bookList);
    }

    @GetMapping("/get-books/name")
    public AjaxResult getBooksByName(@RequestParam(required = false) String bookName){
        List<BookDTO> bookList = this.searchService.searchBooksByName(bookName);
        return success(bookList);
    }
    //todo 内部借口 隔离外部请求
    @GetMapping("/feign/get-books/keyword")
    public List<BookDTO> getBooksByKeywordFeign(String keyword){
        return this.searchService.searchBooksByKeyword(keyword);
    }
    //todo 内部借口 隔离外部请求
    @GetMapping("/feign/get-books/name")
    public List<BookDTO> getBooksByNameFeign(String name){
        return this.searchService.searchBooksByName(name);
    }
}
