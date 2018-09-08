package com.github.cloud.upms.api.feign;

import com.github.cloud.common.core.constant.ServiceNameConstant;
import com.github.cloud.common.core.util.Result;
import com.github.cloud.upms.api.domain.dto.UserDTO;
import com.github.cloud.upms.api.feign.fallback.RemoteUserServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : czk
 * @date 2018-09-06
 */
@FeignClient(value = ServiceNameConstant.UPMS_SERVICE, fallback = RemoteUserServiceFallbackImpl.class)
public interface RemoteUserService {


    /**
     * 根据账户查询用户信息
     *
     * @param account 账号
     * @return 用户信息
     */
    @GetMapping("/user/info/{account}")
    Result<UserDTO> queryUserInfo(@PathVariable("account") String account);

    /**
     * 查询用户
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/user/{userId}")
    Result<UserDTO> queryUser(@PathVariable("userId") Long userId);
}
