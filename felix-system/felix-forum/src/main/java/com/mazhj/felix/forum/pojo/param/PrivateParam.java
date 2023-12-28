package com.mazhj.felix.forum.pojo.param;

import lombok.Data;

/**
 * @author mazhj
 */
@Data
public class PrivateParam {

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
     * 消息接收者
     */
    private String receiver;

    /**
     * 内容
     */
    private String content;

}
