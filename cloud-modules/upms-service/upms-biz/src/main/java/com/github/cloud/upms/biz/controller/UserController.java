package com.github.cloud.upms.biz.controller;

import com.github.cloud.common.core.annotations.RepeatSubmit;
import com.github.cloud.common.core.util.Result;
import com.github.cloud.common.web.util.ServiceHelper;
import com.github.cloud.upms.api.dto.UserDTO;
import com.github.cloud.upms.biz.request.UserInsertRequest;
import com.github.cloud.upms.biz.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 获取用户详情
     *
     * @param account 登录账号
     * @return 用户详情
     */
    @ApiIgnore
    @GetMapping("/info/{account}")
    public Result<UserDTO> queryUserInfo(@PathVariable("account") String account) {
        UserDTO userDTO = userService.queryUserByAccount(account);
        return Objects.nonNull(userDTO) ? Result.success(userDTO) : Result.error("用户不存在");
    }

    /**
     * 用户保存/更新
     *
     * @param userInsertRequest 请求参数
     * @return 成功返回true, 失败返回false
     */
    @RepeatSubmit
    @PostMapping("saveOrUpdate")
    @ApiOperation(value = "用户保存/更新")
    public Result<Boolean> saveOrUpdate(@RequestBody UserInsertRequest userInsertRequest) {
        return ServiceHelper.execute(userInsertRequest, () -> userService.saveOrUpdate(userInsertRequest));
    }
}
