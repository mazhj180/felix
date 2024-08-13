package com.mazhj.common.minio.config;


import io.minio.MinioClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author mazhj
 */
@EnableConfigurationProperties(FelixMinioProperties.class)
public class MinioConfig {

    @Bean
    public MinioClient minioClient(FelixMinioProperties properties){
        return MinioClient.builder()
                .endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
    }
}
