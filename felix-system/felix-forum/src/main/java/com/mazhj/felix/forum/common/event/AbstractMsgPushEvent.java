package com.mazhj.felix.forum.common.event;

import com.mazhj.felix.forum.pojo.ws.WSMsgInfo;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author mazhj
 */
@Getter
public abstract class AbstractMsgPushEvent extends ApplicationEvent {

    protected final WSMsgInfo content;

    protected AbstractMsgPushEvent(Object source, WSMsgInfo content) {
        super(source);
        this.content = content;
    }
}
