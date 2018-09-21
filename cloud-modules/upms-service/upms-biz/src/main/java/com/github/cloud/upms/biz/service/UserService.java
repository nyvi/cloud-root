package com.github.cloud.upms.biz.service;

import com.github.cloud.upms.api.dto.UserDTO;

/**
 * @author : czk
 * @date 2018-09-06 15:46
 */
public interface UserService {

    /**
     * 查询用户
     *
     * @param account 登录账号
     * @return 登录成功返回用户信息, 否则返回null
     */
    UserDTO queryUserByAccount(String account);
}
