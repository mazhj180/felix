package com.mazhj.felix.forum.controller;

import com.mazhj.felix.forum.common.constant.MsgBodyConstant;
import com.mazhj.felix.forum.common.enums.MsgScope;
import com.mazhj.felix.forum.common.event.PrivateMsgPushEvent;
import com.mazhj.felix.forum.common.event.TopicMsgPushEvent;
import com.mazhj.felix.forum.pojo.model.TopicRemark;
import com.mazhj.felix.forum.pojo.param.PrivateParam;
import com.mazhj.felix.forum.pojo.param.TopicRemarkParam;
import com.mazhj.felix.forum.pojo.ws.SenderInfo;
import com.mazhj.felix.forum.pojo.ws.WSMsgInfo;
import com.mazhj.felix.forum.service.TopicRemarkService;
import com.mazhj.felix.forum.websocket.container.SensitiveWordsTire;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

   private final TopicRemarkService topicRemarkService;

    public MessageController(
            ApplicationEventPublisher publisher,
            SensitiveWordsTire sensitiveWordsTire,
            TopicRemarkService topicRemarkService
            ) {
        this.publisher = publisher;
        this.sensitiveWordsTire = sensitiveWordsTire;
        this.topicRemarkService = topicRemarkService;
    }

    @PostMapping("/topic")
    public void msgFormTopic(@RequestBody TopicRemarkParam topicRemarkParam){
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
        Long replyRemarkId = topicRemarkParam.getReplyRemarkId();
        TopicRemark remarksById = this.topicRemarkService.getRemarksById(topicRemarkParam.getTopicId(), replyRemarkId);
        WSMsgInfo wsMsgInfo = new WSMsgInfo(MsgScope.TOPIC, sender, msgBody,remarksById.getUserId());
        publisher.publishEvent(new TopicMsgPushEvent(this, wsMsgInfo));
    }

    @PostMapping("/private")
    public void msgForPrivate(@RequestBody PrivateParam privateParam){
        String healthyContent = this.sensitiveWordsTire.filter(privateParam.getContent()) ;
        SenderInfo sender = new SenderInfo().setUserId(privateParam.getUserId())
                .setNickName(privateParam.getNickName())
                .setHeadImg(privateParam.getHeadImg());
        String receiver = privateParam.getReceiver();
        WSMsgInfo wsMsgInfo = new WSMsgInfo(MsgScope.PRIVATE, sender, healthyContent, receiver);
        publisher.publishEvent(new PrivateMsgPushEvent(this,wsMsgInfo));
    }

    @PostMapping("/group")
    public void msgForGroup(){

    }

}
