package com.mazhj.felix.search.pojo;

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
public class Book {

    /**
     * 图书id
     */
    private String bookId;

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 关键词
     */
    private String keywords;

}
