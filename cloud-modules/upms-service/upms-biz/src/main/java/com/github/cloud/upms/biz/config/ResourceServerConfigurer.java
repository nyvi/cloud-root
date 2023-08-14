package com.github.cloud.upms.biz.config;

import com.github.cloud.common.security.component.AccessDeniedHandler;
import com.github.cloud.common.security.component.ResourceAuthExceptionEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


/**
 * @author : huweihua
 * @date 2023-07-06
 */
@Configuration
@AllArgsConstructor
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    private final AccessDeniedHandler accessDeniedHandler;

    private final ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/actuator/**",
                        "/user/info/**",
                        "/v2/api-docs").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}
