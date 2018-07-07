package com.github.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * oauth2 认证中心
 *
 * @author : czk
 * @date 2018-07-07 09:18
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.github.cloud.auth"})
@Controller
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @GetMapping
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
