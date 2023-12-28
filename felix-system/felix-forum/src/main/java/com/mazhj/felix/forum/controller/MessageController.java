package com.mazhj.felix.forum.controller;

import com.mazhj.felix.forum.common.constant.MsgBodyConstant;
import com.mazhj.felix.forum.common.enums.MsgScope;
import com.mazhj.felix.forum.common.event.PrivateMsgPushEvent;
import com.mazhj.felix.forum.common.event.TopicMsgPushEvent;
import com.mazhj.felix.forum.pojo.param.PrivateParam;
import com.mazhj.felix.forum.pojo.param.TopicRemarkParam;
import com.mazhj.felix.forum.pojo.ws.SenderInfo;
import com.mazhj.felix.forum.pojo.ws.WSMsgInfo;
import com.mazhj.felix.forum.websocket.container.SensitiveWordsTire;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mazhj
 */
@RestController
@RequestMapping("/msg")
public class MessageController {

   private final ApplicationEventPublisher publisher;

   private final SensitiveWordsTire sensitiveWordsTire;

    public MessageController(
            ApplicationEventPublisher publisher,
            SensitiveWordsTire sensitiveWordsTire) {
        this.publisher = publisher;
        this.sensitiveWordsTire = sensitiveWordsTire;
    }

    @PostMapping("/topic")
    public void msgFormTopic(TopicRemarkParam topicRemarkParam){
        String healthContent = this.sensitiveWordsTire.filter(topicRemarkParam.getContent());
        SenderInfo sender = new SenderInfo().setUserId(topicRemarkParam.getUserId())
                .setNickName(topicRemarkParam.getNickName())
                .setHeadImg(topicRemarkParam.getHeadImg());
        Map<String,Object> msgBody = new HashMap<>(4){{
           put(MsgBodyConstant.TOPIC,topicRemarkParam.getTopicId());
           put(MsgBodyConstant.ROOT,topicRemarkParam.getRootRemarkId());
           put(MsgBodyConstant.REPLY,topicRemarkParam.getReplyRemarkId());
           put(MsgBodyConstant.HEALTH_CONTENT,healthContent);
        }};
        Long receiver = topicRemarkParam.getTopicId();
        WSMsgInfo wsMsgInfo = new WSMsgInfo(MsgScope.TOPIC, sender, msgBody,receiver);
        publisher.publishEvent(new TopicMsgPushEvent(this, wsMsgInfo));
    }

    @PostMapping("/private")
    public void msgForPrivate(PrivateParam privateParam){
        String healthyContent = this.sensitiveWordsTire.filter(privateParam.getContent()) ;
        SenderInfo sender = new SenderInfo().setUserId(privateParam.getUserId())
                .setNickName(privateParam.getNickName())
                .setHeadImg(privateParam.getHeadImg());
        String receiver = privateParam.getReceiver();
        WSMsgInfo wsMsgInfo = new WSMsgInfo(MsgScope.PRIVATE, sender, healthyContent, receiver);
        publisher.publishEvent(new PrivateMsgPushEvent(this,wsMsgInfo));
    }

}
