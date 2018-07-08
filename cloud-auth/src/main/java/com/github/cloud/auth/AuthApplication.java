package com.github.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * oauth2 认证中心
 *
 * @author : czk
 * @date 2018-07-07 09:18
 */
@EnableAuthorizationServer
@SpringBootApplication(scanBasePackages = {"com.github.cloud.auth", "com.github.cloud.common"})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
