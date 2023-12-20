package com.mazhj.felix.forum.common.event;

import com.mazhj.felix.forum.pojo.MsgBody;

/**
 * @author mazhj
 */
public class PrivateMsgPushEvent extends AbstractMsgPushEvent {

    public PrivateMsgPushEvent(Object source, MsgBody content) {
        super(source,content);
    }

}
