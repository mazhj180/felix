package com.mazhj.felix.book.common.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * @author mazhj
 */
@Getter
public class RefreshCache extends ApplicationEvent {

    private final List<String> cacheKeys;

    public RefreshCache(Object source,List<String> cacheKeys) {
        super(source);
        this.cacheKeys = cacheKeys;
    }
}
