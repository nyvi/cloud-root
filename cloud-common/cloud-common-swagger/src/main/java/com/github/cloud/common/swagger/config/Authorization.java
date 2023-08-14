package com.github.cloud.common.swagger.config;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : huweihua
 * @date 2023-07-22
 */
@Data
public class Authorization {

    /**
     * 鉴权策略ID，需要和SecurityReferences ID保持一致
     */
    private String name = "";

    /**
     * 需要开启鉴权URL的正则
     */
    private String authRegex = "^.*$";

    /**
     * 鉴权作用域列表
     */
    private List<AuthorizationScope> authorizationScopeList = new ArrayList<>();

    /**
     * 认证地址
     */
    private List<String> tokenUrlList = new ArrayList<>();
}
