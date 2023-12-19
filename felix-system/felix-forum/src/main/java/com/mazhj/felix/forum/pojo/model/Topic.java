package com.mazhj.felix.forum.pojo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author mazhj
 */
@Data
public class Topic {

    /** 数据库主键*/
    private Integer id;

    /** 主题唯一id*/
    private String topicId;

    /** 主题名称*/
    private String topicName;

    /** 简介*/
    private String introduction;

    /** 评论条数*/
    private Integer remarkCount;

    /** 封面图片*/
    private String imgUri;

    /** 拥有者id*/
    private String ownerId;

    /** 创建时间*/
    private Date createTime;


}
