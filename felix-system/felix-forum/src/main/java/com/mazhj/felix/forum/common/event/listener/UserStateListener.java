package com.mazhj.felix.forum.common.event.listener;

import com.mazhj.felix.forum.common.event.OfflineEvent;
import com.mazhj.felix.forum.common.event.OnlineEvent;
import com.mazhj.felix.forum.service.ChannelService;
import io.netty.channel.Channel;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author mazhj
 */
@Component
public class UserStateListener {

    private final ChannelService channelService;


    public UserStateListener(ChannelService channelService) {
        this.channelService = channelService;
    }

    @EventListener(classes = OfflineEvent.class)
    public void offline(OfflineEvent event){
        Channel channel = event.getChannel();
        this.channelService.join(channel);
    }

    @EventListener(classes = OnlineEvent.class)
    public void online(OnlineEvent event){
        this.channelService.out(event.getChannel());
    }

}