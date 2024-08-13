package com.mazhj.felix.book.pojo.vo;

import com.mazhj.common.pojo.enums.BookCategory;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author mazhj
 */
@Data
public class BookVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 图书id*/
    private String bookId;

    /** 作者id*/
    private String authorId;

    /** 作者名称*/
    private String authorName;

    /** 图书名称*/
    private String bookName;

    /** 图书分类*/
    private List<BookCategory> categories;

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

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
}
