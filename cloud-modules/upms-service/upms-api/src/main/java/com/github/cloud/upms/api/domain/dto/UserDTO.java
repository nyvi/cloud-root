package com.github.cloud.upms.api.domain.dto;

import lombok.Data;

/**
 * 用户DTO
 *
 * @author : czk
 * @date 2018-09-06
 */
@Data
public class UserDTO {

    /**
     * 用户id
     */
    private Long id;
    /**
     * 账户
     */
    private String account;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String pwd;
}
