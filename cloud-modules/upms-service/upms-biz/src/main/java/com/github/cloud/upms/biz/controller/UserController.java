package com.github.cloud.upms.biz.controller;

import com.github.cloud.common.core.util.Result;
import com.github.cloud.upms.api.domain.dto.UserDTO;
import com.github.cloud.upms.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户控制层
 *
 * @author : czk
 * @date 2018-09-06
 */
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/{userId}")
    @PreAuthorize("@pms.hasPermission('sys_user_query')")
    public Result<UserDTO> queryUser(@PathVariable Long userId){
        System.out.println("--------------------------");
        UserDTO userDTO = userService.queryUser(userId);
        return Result.success(userDTO);
    }

    @RequestMapping("/info/{account}")
    public Result<UserDTO> queryUserInfo(@PathVariable("account") String account) {
        System.out.println("--------------------------" + account);
        UserDTO userDTO = userService.queryUser(1L);
        return Result.success(userDTO);
    }
}
