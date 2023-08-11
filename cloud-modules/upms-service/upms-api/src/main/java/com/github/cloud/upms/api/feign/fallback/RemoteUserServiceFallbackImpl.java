package com.github.cloud.upms.api.feign.fallback;

import com.github.cloud.common.core.util.Result;
import com.github.cloud.upms.api.dto.UserDTO;
import com.github.cloud.upms.api.feign.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * @author : huweihua
 * @date 2023-07-06
 */
@Slf4j
@Component
public class RemoteUserServiceFallbackImpl implements RemoteUserService {

    @Nonnull
    @Override
    public Result<UserDTO> queryUserInfo(String account) {
        log.error("feign 查询用户信息失败:{}", account);
        return Result.error("查询用户信息失败");
    }
}
