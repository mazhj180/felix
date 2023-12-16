package com.mazhj.felix.forum.service;

import com.mazhj.felix.forum.common.enums.MsgScope;
import com.mazhj.felix.forum.pojo.MsgBody;
import io.netty.channel.Channel;

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
     * @param msgBody 消息内容
     * @param scope 消息广播作用域
     * @param scopeId 作用域标识
     */
    void broadcast(MsgBody msgBody, MsgScope scope, String scopeId);

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

}
