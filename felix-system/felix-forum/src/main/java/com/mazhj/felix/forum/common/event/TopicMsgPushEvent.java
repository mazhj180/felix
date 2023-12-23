package com.mazhj.felix.forum.common.event;

import com.mazhj.felix.forum.pojo.MsgBody;
import com.mazhj.felix.forum.pojo.PushEventInfo;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author mazhj
 */
public class TopicMsgPushEvent extends AbstractMsgPushEvent {

    public TopicMsgPushEvent(Object source, PushEventInfo content) {
        super(source,content);
    }
}
