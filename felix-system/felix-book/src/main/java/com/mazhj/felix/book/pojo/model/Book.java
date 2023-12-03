package com.mazhj.felix.book.pojo.model;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Stack;

/**
 * @author mazhj
 */
@Data
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 数据库自增id*/
    private Long id;

    /** 图书id*/
    private String bookId;

    /** 作者id*/
    private String authorId;

    /** 作者名称*/
    private String authorName;

    /** 图书名称*/
    private String bookName;

    /** 图书评分*/
    private Byte score;

    /** 关键词*/
    private String keywords;

    /** 封面图片*/
    private String imgUrl;

    /** 简介*/
    private String introduction;

    /** ISBN*/
    private String isbn;

    /** 字数*/
    private Integer wordCount;

    /** 点赞数*/
    private Integer supportCount;

}
