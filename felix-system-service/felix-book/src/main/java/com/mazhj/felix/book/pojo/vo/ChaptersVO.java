package com.mazhj.felix.book.pojo.vo;

import lombok.Data;

/**
 * @author mazhj
 */
@Data
public class ChaptersVO {
    /** 图书id*/
    private String bookId;

    /** 章节编号*/
    private Integer chapterCode;

    /** 章节名称*/
    private String name;

    /** 当前状态 （0:正常 ；1:锁章）*/
    private Byte status;

}
