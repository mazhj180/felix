package com.mazhj.felix.forum.common.config;

import com.mazhj.felix.forum.common.config.properties.SensitiveWordsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = SensitiveWordsProperties.class)
public class SensitiveWordsConfig {



}
