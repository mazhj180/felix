package com.mazhj.felix.forum.service;

import com.mazhj.felix.forum.pojo.MsgBody;
import com.mazhj.felix.forum.pojo.ws.WSMsgInfo;
import io.netty.channel.Channel;
import org.springframework.context.ApplicationEvent;

/**
 * @author mazhj
 */
public interface ChannelService {

    /**
     * 向用户推送消息
     * @param msgBody 消息内容
     * @param userId 用户id
     */
    void send(MsgBody msgBody,String userId);

    /**
     * 广播消息
     * @param msgInfo 消息实体
     */
    void broadcast(WSMsgInfo msgInfo);

    /**
     * 将连接从容器中移除
     * @param channel 连接
     */
    void out(Channel channel);

    /**
     * 添加连接到容器中
     * @param channel 连接
     */
    void join(Channel channel);

    /**
     *
     * @param event
     */
    void read(ApplicationEvent event);

}
