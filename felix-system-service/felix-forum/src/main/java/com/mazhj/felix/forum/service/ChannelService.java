package com.mazhj.felix.forum.service;

import com.mazhj.felix.forum.pojo.ws.WSMsgInfo;
import io.netty.channel.Channel;
import org.springframework.context.ApplicationEvent;

/**
 * @author mazhj
 */
public interface ChannelService {

    /**
     * 向用户推送消息
     * @param msgInfo 消息内容
     * @param userId 用户id
     */
    void send(WSMsgInfo msgInfo,String userId);

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
     * 是否已经登录
     * @param userId 用户id
     * @return 登录 true ； 为登录 false；
     */
    boolean isExited(String userId);

}
