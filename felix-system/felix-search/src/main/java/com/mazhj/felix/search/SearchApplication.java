package com.mazhj.felix.search;

import com.mazhj.felix.feign.homepage.clients.HomepageClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author mazhj
 */
@SpringBootApplication(scanBasePackages = {"com.mazhj.common","com.mazhj.felix.search"})
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class,args);
    }
}
