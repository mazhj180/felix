package com.mazhj.felix.book.service;

import com.mazhj.felix.book.pojo.model.Category;

import java.util.List;

/**
 * @author mazhj
 */
public interface CategoryService {

    /**
     * 根据categoryId获取分类信息
     * @param categoryId 分类id
     * @return 分类信息
     */
    Category getByCategoryId(String categoryId);

    /**
     * 返回所有分类信息
     * @return 所有分类信息
     */
    List<Category> getAllCategories();

}
