package com.mazhj.felix.book.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mazhj
 */
@Data
@Accessors(chain = true)
public class ChapterVO {
    /** 图书id*/
    private String bookId;
    /** 章节编号*/
    private Integer chapterCode;
    /** 图书内容*/
    private String content;
}
