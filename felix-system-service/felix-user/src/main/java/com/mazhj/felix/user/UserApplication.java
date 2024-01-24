package com.mazhj.felix.user;

import com.mazhj.felix.feign.book.clients.BookClient;
import com.mazhj.felix.feign.search.clients.SearchClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author mazhj
 */
@SpringBootApplication(scanBasePackages = {"com.mazhj.common","com.mazhj.felix.user"})
@EnableFeignClients(clients = {BookClient.class, SearchClient.class})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
