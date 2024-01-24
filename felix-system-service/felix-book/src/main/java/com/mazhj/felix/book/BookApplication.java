package com.mazhj.felix.book;

import com.mazhj.felix.feign.homepage.clients.HomepageClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author mazhj
 */
@SpringBootApplication(scanBasePackages = {"com.mazhj.common","com.mazhj.felix.book"})
@EnableFeignClients(clients = {HomepageClient.class})
public class BookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class,args);
    }
}
