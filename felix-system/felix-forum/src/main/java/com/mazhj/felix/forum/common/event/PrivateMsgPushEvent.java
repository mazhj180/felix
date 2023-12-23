package com.mazhj.felix.forum.common.event;

import com.mazhj.felix.forum.pojo.MsgBody;
import com.mazhj.felix.forum.pojo.PushEventInfo;

/**
 * @author mazhj
 */
public class PrivateMsgPushEvent extends AbstractMsgPushEvent {

    public PrivateMsgPushEvent(Object source, PushEventInfo content) {
        super(source,content);
    }

}
