package com.mazhj.felix.forum.websocket.handler;

import com.alibaba.fastjson2.JSON;
import com.mazhj.felix.forum.common.event.OfflineEvent;
import com.mazhj.felix.forum.common.event.OnlineEvent;
import com.mazhj.felix.forum.pojo.ws.MsgBody;
import com.mazhj.felix.forum.websocket.container.SensitiveWordsTire;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ChannelHandler.Sharable
public class UltimateHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final ApplicationEventPublisher applicationEventPublisher;


    public UltimateHandler(
            ApplicationEventPublisher applicationEventPublisher
            ) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent idleStateEvent){
            if (idleStateEvent.state() == IdleState.READER_IDLE){
                ctx.channel().close();
            }
        }else if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete){
            applicationEventPublisher.publishEvent(new OnlineEvent(this,ctx.channel()));
        }
        super.userEventTriggered(ctx,evt);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String msg = textWebSocketFrame.text();
        MsgBody msgBody = JSON.parseObject(msg).to(MsgBody.class);
        log.debug(msgBody.toString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        applicationEventPublisher.publishEvent(new OfflineEvent(this,ctx.channel()));
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warn("异常发生，异常消息 ={}", cause.getMessage(),cause);
        ctx.channel().close();
    }
}
