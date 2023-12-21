package com.mazhj.felix.forum.common.event.listener;

import com.mazhj.felix.forum.common.event.AbstractMsgPushEvent;
import com.mazhj.felix.forum.common.event.GroupMsgPushEvent;
import com.mazhj.felix.forum.common.event.PrivateMsgPushEvent;
import com.mazhj.felix.forum.common.event.TopicMsgPushEvent;
import com.mazhj.felix.forum.service.ChannelService;
import com.mazhj.felix.forum.service.TopicRemarkService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author mazhj
 */
@Component
public class MsgPushListener {

    private final TopicRemarkService topicRemarkService;

    private final ChannelService channelService;

    public MsgPushListener(
            TopicRemarkService topicRemarkService,
            ChannelService channelService
    ) {
        this.topicRemarkService = topicRemarkService;
        this.channelService = channelService;
    }

    @EventListener(classes = AbstractMsgPushEvent.class)
    public void saveDb(AbstractMsgPushEvent event){
        if (event instanceof TopicMsgPushEvent){

        }
    }

    @EventListener(classes = AbstractMsgPushEvent.class)
    public void pushMsg(AbstractMsgPushEvent event){

    }

}
