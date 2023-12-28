package com.mazhj.felix.forum.common.event;

import com.mazhj.felix.forum.pojo.ws.WSMsgInfo;

/**
 * @author mazhj
 */
public class TopicMsgPushEvent extends AbstractMsgPushEvent {

    public TopicMsgPushEvent(Object source, WSMsgInfo content) {
        super(source,content);
    }
}
