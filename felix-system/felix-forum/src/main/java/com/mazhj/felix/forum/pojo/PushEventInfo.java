package com.mazhj.felix.forum.pojo;

import lombok.Data;

/**
 * @author mazhj
 */
@Data
public class PushEventInfo {
    /** 消息发送者*/
    private String sender;

    /** 消息接受者*/
    private String receiver;

    /** 消息体*/
    private MsgBody msgBody;

    /** 脱敏后消息内容*/
    private String healthContent;

}
