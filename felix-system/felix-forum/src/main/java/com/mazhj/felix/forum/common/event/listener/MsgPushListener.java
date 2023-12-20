package com.mazhj.felix.forum.common.event.listener;

import com.mazhj.felix.forum.common.event.AbstractMsgPushEvent;
import com.mazhj.felix.forum.common.event.GroupMsgPushEvent;
import com.mazhj.felix.forum.common.event.PrivateMsgPushEvent;
import com.mazhj.felix.forum.common.event.TopicMsgPushEvent;
import com.mazhj.felix.forum.service.TopicRemarkService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author mazhj
 */
@Component
public class MsgPushListener {

    private final TopicRemarkService topicRemarkService;

    public MsgPushListener(TopicRemarkService topicRemarkService) {
        this.topicRemarkService = topicRemarkService;
    }


    @EventListener(classes = AbstractMsgPushEvent.class)
    public void saveDb(AbstractMsgPushEvent event){
        if (event instanceof TopicMsgPushEvent){

        } else if (event instanceof GroupMsgPushEvent) {

        } else if (event instanceof PrivateMsgPushEvent){

        }
    }

    @EventListener(classes = GroupMsgPushEvent.class)
    public void pushMsgToTopic(GroupMsgPushEvent event){

    }

}
