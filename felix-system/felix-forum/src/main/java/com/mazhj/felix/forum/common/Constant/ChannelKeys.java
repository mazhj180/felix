package com.mazhj.felix.forum.common.Constant;

import io.netty.util.AttributeKey;

/**
 * @author mazhj
 */
public class ChannelKeys {
    public final static AttributeKey<String> USER_ID = AttributeKey.valueOf("userId");
    public final static AttributeKey<String> TOPIC_ID = AttributeKey.valueOf("topicId");
    public final static AttributeKey<String> GROUP_ID = AttributeKey.valueOf("groupId");
}
