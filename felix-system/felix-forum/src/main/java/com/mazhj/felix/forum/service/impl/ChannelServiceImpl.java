package com.mazhj.felix.forum.service.impl;

import com.alibaba.fastjson2.JSON;
import com.mazhj.felix.forum.common.Constant.ChannelKeys;
import com.mazhj.felix.forum.common.enums.MsgScope;
import com.mazhj.felix.forum.pojo.MsgBody;
import com.mazhj.felix.forum.service.ChannelService;
import com.mazhj.felix.forum.websocket.container.CaffeineChannelContainer;
import com.mazhj.felix.forum.websocket.container.CaffeineTopicContainer;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
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
    public void broadcast(MsgBody msgBody, MsgScope scope, String scopeId) {
        switch (scope){
            case TOPIC -> topic(msgBody,scopeId);
            case REMARK -> remark();
            case PRIVATE -> send(msgBody,scopeId);
            case GROUP -> group();
            default -> throw new IllegalStateException("Unexpected value: " + scope);
        }
    }

    @Override
    public void off(Channel channel) {
        String userId = channel.attr(ChannelKeys.USER_ID).get();
        channelContainer.del(userId);
    }

    private void topic(MsgBody msgBody,String scopeId){
        topicContainer.get(scopeId).forEach(userId -> send(msgBody,userId));
    }

    private void remark(){}


    private void group(){}
}
