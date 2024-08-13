package com.mazhj.felix.search;

import com.mazhj.felix.feign.book.clients.BookClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author mazhj
 */
@EnableFeignClients(clients = {BookClient.class})
@SpringBootApplication(scanBasePackages = {"com.mazhj.common","com.mazhj.felix.search"})
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class,args);
    }
}
