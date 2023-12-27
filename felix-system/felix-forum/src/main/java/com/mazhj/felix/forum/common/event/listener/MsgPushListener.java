package com.mazhj.felix.forum.common.event.listener;

import com.alibaba.fastjson2.JSON;
import com.mazhj.felix.forum.common.enums.MsgScope;
import com.mazhj.felix.forum.common.event.AbstractMsgPushEvent;
import com.mazhj.felix.forum.common.event.GroupMsgPushEvent;
import com.mazhj.felix.forum.common.event.PrivateMsgPushEvent;
import com.mazhj.felix.forum.common.event.TopicMsgPushEvent;
import com.mazhj.felix.forum.pojo.MsgBody;
import com.mazhj.felix.forum.pojo.PushEventInfo;
import com.mazhj.felix.forum.pojo.model.TopicRemark;
import com.mazhj.felix.forum.service.ChannelService;
import com.mazhj.felix.forum.service.TopicRemarkService;
import org.apache.ibatis.annotations.Insert;
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
        PushEventInfo content = event.getContent();
        MsgBody msgBody = content.getMsgBody();
        String originalContent = msgBody.getContent();
        String healthContent = content.getHealthContent();
        Long topicId = Long.valueOf(content.getReceiver());
        String userId = content.getSender();
        TopicRemark topicRemark = JSON.to(TopicRemark.class, content.getMsgBody());
        this.topicRemarkService.saveRemark(topicRemark);
    }

    @Async(value = "magPushThreadPool")
    @EventListener(classes = AbstractMsgPushEvent.class)
    public void pushMsg(AbstractMsgPushEvent event){
        PushEventInfo content = event.getContent();
        MsgBody msgBody = content.getMsgBody();
        if (msgBody.getMsgScope() == MsgScope.TOPIC){
            msgBody.setContent(content.getHealthContent());
        }
        this.channelService.broadcast(msgBody, msgBody.getMsgScope(),content.getReceiver());
    }

}
