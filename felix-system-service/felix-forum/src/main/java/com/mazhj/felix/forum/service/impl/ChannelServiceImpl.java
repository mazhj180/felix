package com.mazhj.felix.forum.service.impl;

import com.mazhj.felix.forum.common.constant.ChannelKeys;
import com.mazhj.felix.forum.common.enums.MsgScope;
import com.mazhj.felix.forum.pojo.ws.WSMsgInfo;
import com.mazhj.felix.forum.service.ChannelService;
import com.mazhj.felix.forum.websocket.container.CaffeineChannelContainer;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

/**
 * @author mazhj
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    private final CaffeineChannelContainer channelContainer;


    public ChannelServiceImpl(
            CaffeineChannelContainer channelContainer
    ) {
        this.channelContainer = channelContainer;
    }


    @Override
    public void send(WSMsgInfo wsMsgInfo, String userId) {
        Channel channel = channelContainer.get(userId);
        channel.writeAndFlush(new TextWebSocketFrame(wsMsgInfo.json()));
    }

    @Override
    public void broadcast(WSMsgInfo wsMsgInfo) {
        String receiver = wsMsgInfo.getReceiverForStr();
        MsgScope scope = wsMsgInfo.getScope();
        switch (scope){
            case TOPIC, PRIVATE -> send(wsMsgInfo,receiver);
            case REMARK -> remark();
            case GROUP -> group();
            case ONLINE_USERS -> onlineU(wsMsgInfo.json());
            default -> throw new IllegalStateException("Unexpected value: " + scope);
        }
    }

    @Override
    public void out(Channel channel) {
        String userId = channel.attr(ChannelKeys.USER_ID).get();
        channelContainer.del(userId);
    }

    @Override
    public void join(Channel channel) {
        String userId = channel.attr(ChannelKeys.USER_ID).get();
        channelContainer.put(userId,channel);
    }

    @Override
    public boolean isExited(String userId) {
        Channel channel = channelContainer.get(userId);
        return channel != null;
    }

    private void remark(){}


    private void group(){}

    private void onlineU(final String message){
        this.channelContainer.foreach()
                .forEach((k,v) -> v.writeAndFlush(new TextWebSocketFrame(message)));
    }
}
