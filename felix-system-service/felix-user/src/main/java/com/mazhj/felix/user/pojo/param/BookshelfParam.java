package com.mazhj.felix.user.pojo.param;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author mazhj
 */
@Data
public class BookshelfParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 用户id*/
    private String userId;

    /** 图书id */
    private String bookId;

    /**
     * 同步类型：
     * 1.新增  2.更新  3.删除
     */
    private Integer syncType;


}