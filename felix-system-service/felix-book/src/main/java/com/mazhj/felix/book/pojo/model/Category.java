package com.mazhj.felix.book.pojo.model;

import lombok.Data;

/**
 * @author mazhj
 */
@Data
public class Category {

    /** 数据库主键*/
    private Long id;

    /** 类别id*/
    private String categoryId;

    /** 类别名称*/
    private String name;
}
