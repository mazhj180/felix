package com.mazhj.felix.forum.websocket.handler;

import com.mazhj.felix.forum.common.Constant.ChannelKeys;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

/**
 * @author mazhj
 */
public class HttpHeaderHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest request){
            Channel channel = ctx.channel();
            HttpHeaders headers = request.headers();
            String userId = headers.getAsString("userId");
            channel.attr(ChannelKeys.USER_ID).set(userId);
            if (headers.contains("topicId")){
                String topicId = headers.getAsString("topicId");
                channel.attr(ChannelKeys.TOPIC_ID).set(topicId);
            }
            if (headers.contains("groupId")){
                String groupId = headers.getAsString("groupId");
                channel.attr(ChannelKeys.GROUP_ID).set(groupId);
            }
        }
    }
}