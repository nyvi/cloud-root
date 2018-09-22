package com.github.cloud.upms.biz.controller;

import com.github.cloud.common.core.util.Result;
import com.github.cloud.upms.api.dto.UserDTO;
import com.github.cloud.upms.biz.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Objects;

/**
 * 系统用户控制层
 *
 * @author : czk
 * @date 2018-09-06
 */
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiIgnore
    @GetMapping("/info/{account}")
    public Result<UserDTO> queryUserInfo(@PathVariable("account") String account) {
        UserDTO userDTO = userService.queryUserByAccount(account);
        return Objects.nonNull(userDTO) ? Result.success(userDTO) : Result.error("用户不存在");
    }
}
