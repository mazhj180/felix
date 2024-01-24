package com.mazhj.felix.forum.common.event;

import io.netty.channel.Channel;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author mazhj
 */
@Getter
public class OfflineEvent extends ApplicationEvent {

    private final Channel channel;

    public OfflineEvent(Object source, Channel channel) {
        super(source);
        this.channel = channel;
    }
}
