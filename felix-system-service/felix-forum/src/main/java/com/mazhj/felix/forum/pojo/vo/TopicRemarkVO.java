package com.mazhj.felix.forum.pojo.vo;

import com.mazhj.common.core.utils.Convert;
import com.mazhj.common.core.utils.SpringUtil;
import com.mazhj.felix.forum.mapper.TopicRemarkMapper;
import com.mazhj.felix.forum.pojo.model.TopicRemark;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author mazhj
 */
@Data
public class TopicRemarkVO {

    /**
     * 评论ID。
     */
    private Long remarkId;

    /**
     * 根评论的ID。如果这是根评论，则为null。
     */
    private Long rootRemarkId;

    /**
     * 回复的评论ID。如果这是根评论，则为null。
     */
    private Long replyRemarkId;

    /**
     * 回复的用户
     */
    private String replyUser;

    /**
     * 所属话题ID。
     */
    private Long topicId;

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
     * 脱敏内容。
     */
    private String healthyContent;

    /**
     * 评论的点赞数。
     */
    private Long supportCount;

    /** 评论时间*/
    private String createTime;

    /** 子评论*/
    private List<TopicRemarkVO> replies;


    public TopicRemarkVO replyUserHandler(){
        if (this.rootRemarkId != null && this.replyRemarkId != null && !this.replyRemarkId.equals(this.rootRemarkId)) {
             this.replyUser = SpringUtil.getBean(TopicRemarkMapper.class)
                    .selectRemarksById(this.topicId, this.replyRemarkId)
                    .getNickName();
        }
        return this;
    }

}
