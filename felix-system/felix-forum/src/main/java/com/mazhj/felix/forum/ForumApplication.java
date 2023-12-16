package com.mazhj.felix.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mazhj
 */
@SpringBootApplication(scanBasePackages = {"com.mazhj.common","com.mazhj.felix.forum"})
public class ForumApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class,args);
    }
}
