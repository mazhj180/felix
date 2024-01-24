package com.mazhj.felix.book.common.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author mazhj
 */
@Getter
public class ClickEvent extends ApplicationEvent {

    private final String bookId;

    private final ClickType clickType;

    public ClickEvent(Object source,String bookId ,ClickType clickType) {
        super(source);
        this.bookId = bookId;
        this.clickType = clickType;
    }

    public enum ClickType{
        /** 浏览*/
        LOOK_THROUGH,
        /** 阅读*/
        READ
    }
}
