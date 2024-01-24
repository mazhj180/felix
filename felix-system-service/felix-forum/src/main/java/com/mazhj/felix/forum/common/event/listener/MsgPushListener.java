package com.mazhj.felix.forum.common.event.listener;

import com.alibaba.fastjson2.JSON;
import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
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

    private final RedisService redisService;

    public MsgPushListener(
            TopicRemarkService topicRemarkService,
            ChannelService channelService,
            RedisService redisService
    ) {
        this.topicRemarkService = topicRemarkService;
        this.channelService = channelService;
        this.redisService = redisService;
    }

    @Async(value = "saveDbThreadPool")
    @EventListener(classes = TopicMsgPushEvent.class)
    public void saveDb(TopicMsgPushEvent event){
        WSMsgInfo wsMsgInfo = event.getContent();
        SenderInfo sender = wsMsgInfo.getSender();
        TopicRemark topicRemark = JSON.to(TopicRemark.class, wsMsgInfo.jsonMsgBody());
        BeanUtils.copyProperties(sender,topicRemark);
        this.topicRemarkService.saveRemark(topicRemark);
        String key = KeyBuilder.Forum.getTopicRemarkKey(topicRemark.getTopicId().toString());
        if (this.redisService.hasKey(key)){
            this.redisService.remove(key);
        }
    }

    @Async(value = "msgPushThreadPool")
    @EventListener(classes = AbstractMsgPushEvent.class)
    public void pushMsg(AbstractMsgPushEvent event){
        WSMsgInfo wsMsgInfo = event.getContent();
        this.channelService.broadcast(wsMsgInfo);
    }

}
