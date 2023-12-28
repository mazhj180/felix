package com.mazhj.felix.forum.service.impl;

import com.alibaba.fastjson2.JSON;
import com.mazhj.felix.forum.common.constant.ChannelKeys;
import com.mazhj.felix.forum.common.enums.MsgScope;
import com.mazhj.felix.forum.pojo.MsgBody;
import com.mazhj.felix.forum.pojo.ws.WSMsgInfo;
import com.mazhj.felix.forum.service.ChannelService;
import com.mazhj.felix.forum.websocket.container.CaffeineChannelContainer;
import com.mazhj.felix.forum.websocket.container.CaffeineTopicContainer;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

/**
 * @author mazhj
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    private final CaffeineChannelContainer channelContainer;

    private final CaffeineTopicContainer topicContainer;

    public ChannelServiceImpl(
            CaffeineChannelContainer channelContainer,
            CaffeineTopicContainer topicContainer
    ) {
        this.channelContainer = channelContainer;
        this.topicContainer = topicContainer;
    }


    @Override
    public void send(MsgBody msgBody, String userId) {
        Channel channel = channelContainer.get(userId);
        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(msgBody)));
    }

    @Override
    public void broadcast(WSMsgInfo wsMsgInfo) {
//        switch (wsMsgInfo.getScope()){
//            case TOPIC -> topic(msgBody,scopeId);
//            case REMARK -> remark();
//            case PRIVATE -> send(msgBody,scopeId);
//            case GROUP -> group();
//            default -> throw new IllegalStateException("Unexpected value: " + wsMsgInfo.getScope());
//        }
    }

    @Override
    public void out(Channel channel) {
        String userId = channel.attr(ChannelKeys.USER_ID).get();
        if (channel.hasAttr(ChannelKeys.TOPIC_ID)) {
            topicContainer.remove(channel.attr(ChannelKeys.TOPIC_ID).get(),userId);
        }
        if (channel.hasAttr(ChannelKeys.GROUP_ID)) {
//            channelContainer.del(channel.attr(ChannelKeys.GROUP_ID).get());
        }
        channelContainer.del(userId);
    }

    @Override
    public void join(Channel channel) {
        String userId = channel.attr(ChannelKeys.USER_ID).get();
        channelContainer.put(userId,channel);
        if (channel.hasAttr(ChannelKeys.TOPIC_ID)) {
            topicContainer.add(channel.attr(ChannelKeys.TOPIC_ID).get(),userId);
        }
        if (channel.hasAttr(ChannelKeys.GROUP_ID)) {
//            channelContainer.del(channel.attr(ChannelKeys.GROUP_ID).get());
        }
    }

    @Override
    public void read(ApplicationEvent event) {

    }

    private void topic(MsgBody msgBody,String scopeId){
        topicContainer.get(scopeId).forEach(userId -> send(msgBody,userId));
    }

    private void remark(){}


    private void group(){}
}
