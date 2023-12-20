package com.mazhj.felix.forum.common.event;

import com.mazhj.felix.forum.pojo.MsgBody;

/**
 * @author mazhj
 */
public class GroupMsgPushEvent extends AbstractMsgPushEvent {

    public GroupMsgPushEvent(Object source, MsgBody content) {
        super(source,content);

    }

}
