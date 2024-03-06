package com.mazhj.felix.forum.common.event;

import com.mazhj.felix.forum.pojo.ws.WSMsgInfo;

/**
 * @author mazhj
 */
public class AdminMsgPushEvent extends AbstractMsgPushEvent {

    public AdminMsgPushEvent(Object source, WSMsgInfo content) {
        super(source, content);
    }
}
