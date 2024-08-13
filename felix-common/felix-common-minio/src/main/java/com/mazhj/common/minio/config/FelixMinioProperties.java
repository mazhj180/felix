package com.mazhj.common.minio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mazhj
 */
@Data
@ConfigurationProperties(prefix = "minio")
public class FelixMinioProperties {

    private String endpoint;

    private String accessKey;

    private String secretKey;
}
