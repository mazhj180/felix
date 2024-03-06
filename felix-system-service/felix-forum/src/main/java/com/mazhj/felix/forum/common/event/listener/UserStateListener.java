package com.mazhj.felix.forum.common.event.listener;

import com.mazhj.felix.forum.common.constant.ChannelKeys;
import com.mazhj.felix.forum.common.enums.MsgScope;
import com.mazhj.felix.forum.common.event.OfflineEvent;
import com.mazhj.felix.forum.common.event.OnlineEvent;
import com.mazhj.felix.forum.pojo.ws.SenderInfo;
import com.mazhj.felix.forum.pojo.ws.WSMsgInfo;
import com.mazhj.felix.forum.service.ChannelService;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author mazhj
 */
@Slf4j
@Component
public class UserStateListener {

    private final ChannelService channelService;


    public UserStateListener(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Async(value = "msgPushThreadPool")
    @EventListener(classes = OfflineEvent.class)
    public void offline(OfflineEvent event){
        Channel channel = event.getChannel();
        this.channelService.out(channel);
        String userId = channel.attr(ChannelKeys.USER_ID).get();
        log.info("用户 [ {} ] 下线",userId);
    }

    @Async(value = "msgPushThreadPool")
    @EventListener(classes = OnlineEvent.class)
    public void online(OnlineEvent event){
        Channel channel = event.getChannel();
        String userId = channel.attr(ChannelKeys.USER_ID).get();
        if (this.channelService.isExited(userId)){
            SenderInfo sender = new SenderInfo().setUserId("System");
            new WSMsgInfo(MsgScope.PRIVATE,sender,"已在其他设备登录",userId);
            this.channelService.out(channel);
            log.info("用户 [ {} ] 切换设备",userId);
        }
        this.channelService.join(channel);
        log.info("用户 [ {} ] 上线 ",userId);
    }

}
