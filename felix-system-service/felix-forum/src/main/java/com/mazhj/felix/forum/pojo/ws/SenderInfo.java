package com.mazhj.felix.forum.pojo.ws;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mazhj
 */
@Data
@Accessors(chain = true)
public class SenderInfo {

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

}
