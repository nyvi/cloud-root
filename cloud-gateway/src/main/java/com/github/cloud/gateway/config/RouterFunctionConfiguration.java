package com.github.cloud.gateway.config;

import com.github.cloud.gateway.handler.SwaggerResourceHandler;
import com.github.cloud.gateway.handler.SwaggerSecurityHandler;
import com.github.cloud.gateway.handler.SwaggerUiHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * @author : huweihua
 * @date 2023-07-21 16:39
 */
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {

    private final SwaggerResourceHandler swaggerResourceHandler;

    private final SwaggerSecurityHandler swaggerSecurityHandler;

    private final SwaggerUiHandler swaggerUiHandler;

    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(
                RequestPredicates.GET("/swagger-resources")
                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerResourceHandler)
                .andRoute(RequestPredicates.GET("/swagger-resources/configuration/ui")
                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerUiHandler)
                .andRoute(RequestPredicates.GET("/swagger-resources/configuration/security")
                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerSecurityHandler);

    }
}
