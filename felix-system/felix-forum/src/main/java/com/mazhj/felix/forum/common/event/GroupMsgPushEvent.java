package com.mazhj.felix.forum.common.event;

import com.mazhj.felix.forum.pojo.MsgBody;
import com.mazhj.felix.forum.pojo.PushEventInfo;

/**
 * @author mazhj
 */
public class GroupMsgPushEvent extends AbstractMsgPushEvent {

    public GroupMsgPushEvent(Object source, PushEventInfo content) {
        super(source,content);

    }

}
