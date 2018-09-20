package com.github.cloud.common.core.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis Plus 配置
 *
 * @author : czk
 * @date 2018-09-20
 */
@Configuration
public class MyBatisPlusConfig {

    @Bean
    public ISqlInjector sqlInjector() {
        System.out.println("----------执行了-----------");
        return new LogicSqlInjector();
    }

}
