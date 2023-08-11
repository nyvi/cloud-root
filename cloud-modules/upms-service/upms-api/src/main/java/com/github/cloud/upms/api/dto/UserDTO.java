package com.github.cloud.upms.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户DTO
 *
 * @author : huweihua
 * @date 2023-07-06
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

    /**
     * 角色集合
     */
    private List<String> roleList;

    /**
     * 权限码集合
     */
    private List<String> permissionsList;
}
