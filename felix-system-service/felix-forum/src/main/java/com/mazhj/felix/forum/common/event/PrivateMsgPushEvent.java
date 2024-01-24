package com.mazhj.felix.forum.common.event;

import com.mazhj.felix.forum.pojo.ws.WSMsgInfo;

/**
 * @author mazhj
 */
public class PrivateMsgPushEvent extends AbstractMsgPushEvent {

    public PrivateMsgPushEvent(Object source, WSMsgInfo content) {
        super(source,content);
    }

}
