package com.mazhj.felix.book.pojo.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author mazhj
 */
@Data
public class Chapter implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键id*/
    private Long id;

    /** 图书id*/
    private String bookId;

    /** 章节编号*/
    private Integer chapterCode;

    /** 章节名称*/
    private String name;

    /** 内容*/
    private String contentPath;

    /** 当前状态 （0:正常 ；1:锁章）*/
    private Byte status;

    /** 创建时间*/
    private Date createTime;

    /** 更新时间*/
    private Date updateTime;

}
