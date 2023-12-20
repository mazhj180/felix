package com.mazhj.felix.forum.common.event;

import com.mazhj.felix.forum.pojo.MsgBody;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author mazhj
 */
@Getter
public abstract class AbstractMsgPushEvent extends ApplicationEvent {

    protected final MsgBody content;

    protected AbstractMsgPushEvent(Object source, MsgBody content) {
        super(source);
        this.content = content;
    }
}
