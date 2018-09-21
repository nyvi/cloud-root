package com.github.cloud.upms.biz;

import com.github.cloud.common.swagger.annotation.EnableCloudSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : czk
 * @date 2018-06-29
 */
@EnableFeignClients
@EnableCloudSwagger2
@SpringCloudApplication
public class UpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpmsApplication.class, args);
    }

}
