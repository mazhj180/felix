package com.mazhj.felix.gateway.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mazhj
 */
@Data
@ConfigurationProperties(prefix = "spring.cloud.gateway")
public class GatewayConfigProperties {

    private List<String> whiteList = new ArrayList<>();

    private String authCenterUrl;

}
