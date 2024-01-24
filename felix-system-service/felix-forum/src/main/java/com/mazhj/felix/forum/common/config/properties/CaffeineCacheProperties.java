package com.mazhj.felix.forum.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mazhj
 */
@Data
@ConfigurationProperties(prefix = "channel-container")
public class CaffeineCacheProperties {

    public CaffeineChannelProperties channel = new CaffeineChannelProperties();

    public CaffeineTopicProperties topic = new CaffeineTopicProperties();


    @Data
    public static final class CaffeineChannelProperties{

        private Integer initialCapacity = 1000;

        private Long maximumSize = 10000L;

        private Long maximumWeight;
    }

    @Data
    public static final class CaffeineTopicProperties{

        private Integer initialCapacity = 100;

        private Long maximumSize = 1000L;

        private Long maximumWeight;
    }
}
