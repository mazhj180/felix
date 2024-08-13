package com.mazhj.common.pojo.dto;

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
public class BookDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 图书id
     */
    private String bookId;

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 作者
     */
    private String authorId;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 分类
     */
    private List<BookCategory> categories;

    /**
     * 图书评分
     */
    private Byte score;

    /**
     * 关键词
     */
    private String keywords;

    /**
     * 封面
     */
    private String imgUrl;

    /**
     * 简介
     */
    private String introduction;

    /**
     * isbn
     */
    private String isbn;

    /**
     * 字数
     */
    private Integer wordCount;

    /** 点赞数*/
    private Integer supportCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
