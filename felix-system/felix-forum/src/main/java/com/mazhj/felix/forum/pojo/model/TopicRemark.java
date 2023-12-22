package com.mazhj.felix.forum.pojo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author mazhj
 */
@Data
public class TopicRemark {

    /**
     * 数据库主键。
     */
    private Long id;

    /**
     * 评论ID。
     */
    private String remarkId;

    /**
     * 根评论的ID。如果这是根评论，则为null。
     */
    private String rootRemarkId;

    /**
     * 回复的评论ID。如果这是根评论，则为null。
     */
    private String replyRemarkId;

    /**
     * 所属话题ID。
     */
    private String topicId;

    /**
     * 用户ID。
     */
    private String userId;

    /**
     * 用户评论时的昵称。
     */
    private String nickName;

    /**
     * 用户头像。可能为null。
     */
    private String headImg;

    /**
     * 评论内容。
     */
    private String content;

    /**
     * 脱敏内容。
     */
    private String healthyContent;

    /**
     * 评论的点赞数。
     */
    private Long supportCount;

    /** 评论时间*/
    private Date createTime;

}