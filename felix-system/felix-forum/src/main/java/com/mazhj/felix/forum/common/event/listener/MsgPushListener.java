package com.mazhj.felix.forum.common.event.listener;

import com.alibaba.fastjson2.JSON;
import com.mazhj.felix.forum.common.event.AbstractMsgPushEvent;
import com.mazhj.felix.forum.common.event.TopicMsgPushEvent;
import com.mazhj.felix.forum.pojo.model.TopicRemark;
import com.mazhj.felix.forum.pojo.ws.SenderInfo;
import com.mazhj.felix.forum.pojo.ws.WSMsgInfo;
import com.mazhj.felix.forum.service.ChannelService;
import com.mazhj.felix.forum.service.TopicRemarkService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
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

    @Async(value = "saveDbThreadPool")
    @EventListener(classes = TopicMsgPushEvent.class)
    public void saveDb(TopicMsgPushEvent event){
        WSMsgInfo wsMsgInfo = event.getContent();
        SenderInfo sender = wsMsgInfo.getSender();
        TopicRemark topicRemark = JSON.to(TopicRemark.class, wsMsgInfo.jsonMsgBody());
        BeanUtils.copyProperties(sender,topicRemark);
        this.topicRemarkService.saveRemark(topicRemark);
    }

    @Async(value = "magPushThreadPool")
    @EventListener(classes = AbstractMsgPushEvent.class)
    public void pushMsg(AbstractMsgPushEvent event){
        WSMsgInfo wsMsgInfo = event.getContent();
        this.channelService.broadcast(wsMsgInfo);
    }

}
