package com.github.cloud.common.web.config;

import com.github.cloud.common.web.filter.HttpServletRequestReplacedFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 拦截器配置
 *
 * @author : huweihua
 * @date 2023-07-07
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> filterRegister() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new HttpServletRequestReplacedFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

}
