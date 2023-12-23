package com.mazhj.felix.forum.common.event;

import com.mazhj.felix.forum.pojo.MsgBody;
import com.mazhj.felix.forum.pojo.PushEventInfo;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author mazhj
 */
@Getter
public abstract class AbstractMsgPushEvent extends ApplicationEvent {

    protected final PushEventInfo content;

    protected AbstractMsgPushEvent(Object source, PushEventInfo content) {
        super(source);
        this.content = content;
    }
}
