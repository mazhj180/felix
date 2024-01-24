package com.mazhj.felix.book.service.impl;

import com.mazhj.felix.book.mapper.CategoryMapper;
import com.mazhj.felix.book.pojo.model.Category;
import com.mazhj.felix.book.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mazhj
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category getByCategoryId(String categoryId) {
        return this.categoryMapper.selectOneByCategoryId(categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryMapper.select();
    }
}
