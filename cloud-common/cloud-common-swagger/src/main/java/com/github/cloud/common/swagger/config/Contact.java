package com.github.cloud.common.swagger.config;

import lombok.Data;

/**
 * @author : czk
 * @date 2018-09-22
 */
@Data
public class Contact {

    /**
     * 联系人
     */
    private String name;

    /**
     * 联系人url
     */
    private String url;

    /**
     * 联系人email
     */
    private String email;
}
