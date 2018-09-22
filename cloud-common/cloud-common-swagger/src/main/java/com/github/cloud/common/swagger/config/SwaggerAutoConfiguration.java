package com.github.cloud.common.swagger.config;

import com.github.cloud.common.core.util.CollectionUtils;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * swagger 配置
 *
 * @author : czk
 * @date 2018-09-21
 */
@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerAutoConfiguration {

    private static final String DEFAULT_EXCLUDE_PATH = "/error";

    private static final String BASE_PATH = "/**";

    @Bean
    @ConditionalOnMissingBean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }

    @Bean
    public Docket api(SwaggerProperties swaggerProperties) {

        // base-path处理
        if (CollectionUtils.isEmpty(swaggerProperties.getBasePath())) {
            swaggerProperties.getBasePath().add(BASE_PATH);
        }
        List<Predicate<String>> basePath = swaggerProperties.getBasePath().stream().map(PathSelectors::ant).collect(Collectors.toList());

        // exclude-path处理
        if (CollectionUtils.isEmpty(swaggerProperties.getExcludePath())) {
            swaggerProperties.getExcludePath().add(DEFAULT_EXCLUDE_PATH);
        }
        List<Predicate<String>> excludePath = swaggerProperties.getExcludePath().stream().map(PathSelectors::ant).collect(Collectors.toList());

        // noinspection Guava
        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerProperties.getHost())
                .apiInfo(apiInfo(swaggerProperties)).select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(Predicates.and(Predicates.not(Predicates.or(excludePath)), Predicates.or(basePath)))
                .build()
                .securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext()))
                .pathMapping("/");
    }

    /**
     * 配置默认的全局鉴权策略的开关，通过正则表达式进行匹配；默认匹配所有URL
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(swaggerProperties().getAuthorization().getAuthRegex()))
                .build();
    }

    /**
     * 默认的全局鉴权策略
     */
    private List<SecurityReference> defaultAuth() {
        List<AuthorizationScope> authorizationScopeList = swaggerProperties().getAuthorization().getAuthorizationScopeList().stream().map(r -> new AuthorizationScope(r.getScope(), r.getDescription())).collect(Collectors.toList());
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[authorizationScopeList.size()];
        return Collections.singletonList(SecurityReference.builder()
                .reference(swaggerProperties().getAuthorization().getName())
                .scopes(authorizationScopeList.toArray(authorizationScopes))
                .build());
    }


    private OAuth securitySchema() {
        Authorization authorization = swaggerProperties().getAuthorization();
        List<AuthorizationScope> scopeList = authorization.getAuthorizationScopeList().stream().map(r -> new AuthorizationScope(r.getScope(), r.getDescription())).collect(Collectors.toList());
        List<GrantType> grantTypes = authorization.getTokenUrlList().stream().map(ResourceOwnerPasswordCredentialsGrant::new).collect(Collectors.toList());
        return new OAuth(authorization.getName(), scopeList, grantTypes);
    }

    private ApiInfo apiInfo(SwaggerProperties properties) {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .termsOfServiceUrl(properties.getTermsOfServiceUrl())
                .contact(new Contact(properties.getContact().getName(), properties.getContact().getUrl(), properties.getContact().getEmail()))
                .license(properties.getLicense())
                .licenseUrl(properties.getLicenseUrl())
                .version(properties.getVersion())
                .build();
    }
}
