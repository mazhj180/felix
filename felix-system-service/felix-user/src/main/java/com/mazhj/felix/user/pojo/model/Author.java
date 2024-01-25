package com.mazhj.felix.user.pojo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author mazhj
 */
@Data
public class Author {

    /**
     * 数据库主键
     */
    private Integer id;

    /**
     * 作者ID
     */
    private String authorId;

    /**
     * 身份证号
     */
    private String identityCard;

    /**
     * 真实名字
     */
    private String realName;

    /**
     * 笔名
     */
    private String pseudonym;

    /**
     * 国籍
     */
    private String nationality;

    /**
     * 出生日
     */
    private Date birthday;

    /**
     * 代表作
     */
    private String masterWorks;

    /**
     * 作者简介
     */
    private String introduction;

}
