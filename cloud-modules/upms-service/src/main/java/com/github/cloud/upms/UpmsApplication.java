package com.github.cloud.upms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : czk
 * @date 2018-06-29 16:11
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.github.cloud")
public class UpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpmsApplication.class, args);
    }

}
