package com.mazhj.common.redis.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

/**
 * @author mazhj
 */
@ConditionalOnMissingBean(
        {RedisService2.class}
)
public class RedisService2 {
}
