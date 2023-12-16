package com.mazhj.felix.forum.websocket.handler;

import com.alibaba.fastjson2.JSON;
import com.mazhj.felix.forum.pojo.MsgBody;
import com.mazhj.felix.forum.service.ChannelService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ChannelHandler.Sharable
public class UltimateHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final ChannelService channelService;

    public UltimateHandler(ChannelService channelService) {
        this.channelService = channelService;
    }

    private void shutOff(ChannelHandlerContext ctx){
        channelService.out(ctx.channel());
        ctx.channel().close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent idleStateEvent){
            if (idleStateEvent.state() == IdleState.READER_IDLE){
                shutOff(ctx);
            }
        }else if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete){
            System.out.println("握手success");
            channelService.join(ctx.channel());
        }

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
//        MsgBody msgBody = JSON.to(MsgBody.class, textWebSocketFrame.text());
        System.out.println(textWebSocketFrame.text());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        shutOff(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warn("异常发生，异常消息 ={}", cause);
        ctx.channel().close();
    }
}
