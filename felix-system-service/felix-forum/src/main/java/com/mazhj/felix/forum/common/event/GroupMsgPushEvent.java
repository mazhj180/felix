package com.mazhj.felix.forum.common.event;

import com.mazhj.felix.forum.pojo.ws.WSMsgInfo;

/**
 * @author mazhj
 */
public class GroupMsgPushEvent extends AbstractMsgPushEvent {

    public GroupMsgPushEvent(Object source, WSMsgInfo content) {
        super(source,content);

    }

}
