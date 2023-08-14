package com.github.cloud.common.swagger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : huweihua
 * @date 2023-07-21
 */
@Data
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    /**
     * swagger会解析的包路径
     **/
    private String basePackage = "";
    /**
     * swagger会解析的url规则
     **/
    private List<String> basePath = new ArrayList<>();
    /**
     * 在basePath基础上需要排除的url规则
     **/
    private List<String> excludePath = new ArrayList<>();
    /**
     * 标题
     **/
    private String title;
    /**
     * 描述
     **/
    private String description;
    /**
     * 版本
     **/
    private String version;
    /**
     * 许可证
     **/
    private String license;
    /**
     * 许可证URL
     **/
    private String licenseUrl;
    /**
     * 服务条款URL
     **/
    private String termsOfServiceUrl;

    /**
     * host信息
     **/
    private String host;
    /**
     * 联系人信息
     */
    private Contact contact = new Contact();
    /**
     * 全局统一鉴权配置
     **/
    private Authorization authorization = new Authorization();
}
