package com.mazhj.felix.book.pojo.vo;

import lombok.Data;

/**
 * @author mazhj
 */
@Data
public class RankVO {
    /** 图书id*/
    private String bookId;

    /** 作者名称*/
    private String authorName;

    /** 图书名称*/
    private String bookName;

    /** 封面图片*/
    private String imgUrl;

    /** 点赞数*/
    private Integer supportCount;

    /** 图书评分*/
    private Byte score;

    /** 热度*/
    private Double hot;
}
