package com.github.cloud.common.web.config;

import com.github.cloud.common.web.interceptor.RepeatSubmitInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : huweihua
 * @date 2023-07-02
 */
@Component
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final RepeatSubmitInterceptor repeatSubmitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
    }

}
