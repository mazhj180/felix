package com.mazhj.felix.forum.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mazhj
 */
@Data
@ConfigurationProperties(prefix = "thread-pool")
public class ThreadPoolProperties {

    private final MsgPushThreadPool msgPushThreadPool = new MsgPushThreadPool();

    private final SaveDbThreadPool saveDbThreadPool = new SaveDbThreadPool();

    @Data
    public static class MsgPushThreadPool{
        private int coreThreadSize = 1;

        private int maximumThreadSize = 5;

        private long keepAliveTime = 0;

        private int queueCapacity = 2048;

        private String threadNamePrefix = "thread-name-prefix";
    }

    @Data
    public static class SaveDbThreadPool{
        private int coreThreadSize = 1;

        private int maximumThreadSize = 5;

        private long keepAliveTime = 0;

        private int queueCapacity = 2048;

        private String threadNamePrefix = "thread-name-prefix";
    }
}
