package com.mazhj.felix.gateway.config;

import com.mazhj.felix.gateway.config.properties.GatewayConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author mazhj
 */
@Configuration
@EnableConfigurationProperties(value = {GatewayConfigProperties.class})
public class GatewayConfig {

    @Bean
    public WebClient webClient(GatewayConfigProperties properties) {
        return WebClient.builder()
                .baseUrl(properties.getAuthCenterUrl())
                .build();
    }

}
