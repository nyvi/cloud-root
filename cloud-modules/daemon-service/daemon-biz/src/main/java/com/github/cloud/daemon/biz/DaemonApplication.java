package com.github.cloud.daemon.biz;

import com.github.cloud.common.swagger.annotation.EnableCloudSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 调度中心
 *
 * @author : czk
 * @date 2018-11-23
 */
@EnableScheduling
@EnableFeignClients
@EnableCloudSwagger2
@SpringCloudApplication
public class DaemonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaemonApplication.class, args);
    }
}
