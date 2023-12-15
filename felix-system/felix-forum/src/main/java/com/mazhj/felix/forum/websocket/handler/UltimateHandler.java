package com.mazhj.felix.forum.websocket.handler;

import com.mazhj.felix.forum.service.ChannelService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class UltimateHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final ChannelService msgService;

    public UltimateHandler(ChannelService msgService) {
        this.msgService = msgService;
    }

    private void shutOff(ChannelHandlerContext ctx){
        //todo 业务逻辑
        ctx.channel().close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent idleStateEvent){
            if (idleStateEvent.state() == IdleState.READER_IDLE){
                //todo 关闭连接
                shutOff(ctx);
            }
        }else if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete){
            //todo 握手认证成功
        }

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

    }
}
