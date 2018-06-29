package com.github.cloud.upms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : czk
 * @date 2018-06-29 16:24
 */
@RestController
public class HealthyController {

    @GetMapping("healthy")
    public String healthy() {
        return "ok";
    }
}
