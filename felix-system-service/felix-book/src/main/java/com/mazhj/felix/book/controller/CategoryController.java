package com.mazhj.felix.book.controller;

import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.book.pojo.model.Category;
import com.mazhj.felix.book.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mazhj
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get-all-categories")
    public AjaxResult getAllCategories(){
        List<Category> categories = this.categoryService.getAllCategories();
        return success(categories);
    }
}
