package com.mazhj.felix.forum.common.constant;

import io.netty.util.AttributeKey;

/**
 * @author mazhj
 */
public class ChannelKeys {
    public final static AttributeKey<String> USER_ID = AttributeKey.valueOf("userId");
    public final static AttributeKey<String> TOPIC_ID = AttributeKey.valueOf("topicId");
    public final static AttributeKey<String> GROUP_ID = AttributeKey.valueOf("groupId");
    public final static AttributeKey<String> RECEIVER_ID = AttributeKey.valueOf("receiverId");
}
