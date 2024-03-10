package com.mazhj.felix.control;

import com.mazhj.felix.feign.book.clients.BookClient;
import com.mazhj.felix.feign.search.clients.SearchClient;
import com.mazhj.felix.feign.user.clients.UserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author mazhj
 */
@SpringBootApplication(scanBasePackages = {"com.mazhj.common","com.mazhj.felix.control"})
@EnableFeignClients(clients = {UserClient.class,BookClient.class, SearchClient.class})
public class ControlApplication {
    public static void main(String[] args) {
        SpringApplication.run(ControlApplication.class,args);
    }
}
