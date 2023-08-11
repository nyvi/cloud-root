package com.github.cloud.upms.biz;

import com.github.cloud.common.swagger.annotation.EnableCloudSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : huweihua
 * @date 2023-06-29
 */
@EnableFeignClients
@EnableDiscoveryClient
@EnableCloudSwagger2
@SpringCloudApplication
public class UpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpmsApplication.class, args);
    }

}
