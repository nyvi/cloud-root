package com.github.cloud.upms.api.feign.fallback;

import com.github.cloud.common.core.util.Result;
import com.github.cloud.upms.api.domain.dto.UserDTO;
import com.github.cloud.upms.api.feign.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : czk
 * @date 2018-09-06
 */
@Slf4j
@Component
public class RemoteUserServiceFallbackImpl implements RemoteUserService {

    @Override
    public Result<UserDTO> queryUser(Long userId) {
        log.error("feign 查询用户信息失败:{}", userId);
        return Result.error("查询用户信息失败");
    }
}
