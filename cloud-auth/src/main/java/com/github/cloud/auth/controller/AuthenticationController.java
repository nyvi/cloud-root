package com.github.cloud.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : czk
 * @date 2018-07-08
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    /**
     * 认证页面
     *
     * @return ModelAndView
     */
    @GetMapping("/login.html")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    /**
     * 成功页面
     *
     * @return ModelAndView
     */
    @GetMapping("/success.html")
    public ModelAndView successPage() {
        return new ModelAndView("success");
    }
}
