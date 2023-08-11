package com.github.cloud.common.swagger.config;

import lombok.Data;

/**
 * @author : huweihua
 * @date 2023-07-22
 */
@Data
public class AuthorizationScope {

    /**
     * 作用域名称
     */
    private String scope;

    /**
     * 作用域描述
     */
    private String description;
}
