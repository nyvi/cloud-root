package com.github.cloud.upms.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户DTO
 *
 * @author : czk
 * @date 2018-09-06
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 7999412889198919239L;

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
