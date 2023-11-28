package com.mazhj.felix.homepage;

import com.mazhj.felix.feign.book.clients.BookClient;
import com.mazhj.felix.feign.search.clients.SearchClient;
import com.mazhj.felix.feign.user.clients.UserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author mazhj
 */
@SpringBootApplication
@EnableFeignClients(clients = {UserClient.class,BookClient.class, SearchClient.class})
public class HomepageApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomepageApplication.class,args);
    }
}
