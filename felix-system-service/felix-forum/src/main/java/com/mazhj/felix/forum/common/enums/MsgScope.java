package com.mazhj.felix.forum.common.enums;

/**
 * @author mazhj
 */
public enum MsgScope {
    /** 话题*/
    TOPIC("topic"),
    /** 图书评论*/
    REMARK("remark"),
    /** 用户私聊*/
    PRIVATE("private"),
    /** 群聊*/
    GROUP("group"),
    /** 所有在线用户*/
    ONLINE_USERS("onlineUsers");
    private final String val;

    MsgScope(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
