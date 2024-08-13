package com.mazhj.felix.user.common.event;

import com.mazhj.felix.user.pojo.param.Reason;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ReasonEvent extends ApplicationEvent {

    private final String userId;

    private final Reason reason;

    public ReasonEvent(Object source,String userId,Reason reason) {
        super(source);
        this.userId = userId;
        this.reason = reason;
    }
}
