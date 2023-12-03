package com.mazhj.felix.user.pojo.vo;

import com.mazhj.common.pojo.enums.BookCategory;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author mazhj
 */
@Data
public class BookshelfVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 用户id*/
    private String userId;

    /** 图书id*/
    private String bookId;

    /** 图书名称*/
    private String bookName;

    /** 作者名称*/
    private String authorName;

    /** 封面图片地址*/
    private String imgUrl;

    /** 图书种类*/
    private BookCategory category;
}
