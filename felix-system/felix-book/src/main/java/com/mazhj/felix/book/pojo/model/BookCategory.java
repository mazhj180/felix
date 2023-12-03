package com.mazhj.felix.book.pojo.model;

import lombok.Data;

/**
 * @author mazhj
 */
@Data
public class BookCategory {

    /** 数据库主键*/
    private Long id;

    /** 图书id*/
    private String booId;

    /** 类别id*/
    private String categoryId;
}
