package com.mazhj.felix.forum.pojo.ws;

import com.alibaba.fastjson2.JSON;
import com.mazhj.felix.forum.common.enums.MsgScope;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mazhj
 */
public class WSMsgInfo extends HashMap<String,Object> {

    private final static String SENDER_INFO = "sender";

    private final static String MSG_BODY = "msgBody";

    private final static String SCOPE = "scope";

    private final static String RECEIVER = "receiver";

    public WSMsgInfo(MsgScope scope , SenderInfo sender, Object msgBody, Object receiver){
        super.put(SENDER_INFO,sender);
        super.put(MSG_BODY,msgBody);
        super.put(SCOPE,scope);
        super.put(RECEIVER,receiver);
    }

    public SenderInfo getSender(){
        return (SenderInfo) super.get(SENDER_INFO);
    }

    public MsgScope getScope(){
        return (MsgScope) super.get(SCOPE);
    }

    public <T> T getMsgBody(){
        return (T) super.get(MSG_BODY);
    }

    public Long getReceiverForLong(){
        return (Long) super.get(RECEIVER);
    }

    public String getReceiverForStr(){
        return (String) super.get(RECEIVER);
    }

    public String jsonMsgBody(){
        return JSON.toJSONString(super.get(MSG_BODY));
    }


    public String json(){
        return JSON.toJSONString(this);
    }

}
