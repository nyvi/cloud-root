package com.github.cloud.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : huweihua
 * @date 2023-07-21
 */
@Primary
@Component
@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {

    public static final String API_URI = "/v2/api-docs";

    private final RouteLocator routeLocator;

    private final GatewayProperties gatewayProperties;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        for (RouteDefinition routeDefinition : gatewayProperties.getRoutes()) {
            if (routes.contains(routeDefinition.getId())) {
                routeDefinition.getPredicates().stream()
                        .filter(f -> "Path".equalsIgnoreCase(f.getName()))
                        .filter(f -> !"cloud-auth".equalsIgnoreCase(routeDefinition.getId()))
                        .forEach(r -> resources.add(swaggerResource(routeDefinition.getId(), r.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", API_URI))));
            }
        }
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

}
