package com.mazhj.felix.forum.pojo.model;

import lombok.Data;

/**
 * @author mazhj
 */
@Data
public class Topic {

    /** 主题唯一id*/
    private Long topicId;

    /** 主题名称*/
    private String topicName;

    /** 简介*/
    private String introduction;

    /** 评论条数*/
    private Long remarkCount;

    /** 封面图片*/
    private String imgUri;

    /** 拥有者id*/
    private String creator;

    /** 创建时间*/
    private String createTime;


}
