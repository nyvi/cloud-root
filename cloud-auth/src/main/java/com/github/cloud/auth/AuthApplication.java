package com.github.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * oauth2 认证中心
 *
 * @author : huweihua
 * @date 2023-07-07
 */
@EnableDiscoveryClient
@EnableFeignClients({"com.github.cloud.upms.api.feign"})
@SpringCloudApplication
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
