package com.mazhj.felix.book.common.event.listener;

import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.felix.book.common.event.ClickEvent;
import com.mazhj.felix.feign.homepage.clients.HomepageClient;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author mazhj
 */
@Component
public class HotPotListener {

    private final HomepageClient homepageClient;

    public HotPotListener(HomepageClient homepageClient) {
        this.homepageClient = homepageClient;
    }


    @EventListener(classes = ClickEvent.class)
    public void record(ClickEvent event){
        String bookId = event.getBookId();
        double hot = switch (event.getClickType()){
            case READ -> 5;
            case LOOK_THROUGH -> 2;
        };
        this.homepageClient.incrHot(bookId,hot);
    }
}
