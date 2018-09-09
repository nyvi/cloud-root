package com.github.cloud.auth.security;

import com.github.cloud.common.core.constant.enums.ResultCode;
import com.github.cloud.common.core.util.Result;
import com.github.cloud.upms.api.dto.UserDTO;
import com.github.cloud.upms.api.feign.RemoteUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 用户信息查询服务
 *
 * @author : czk
 * @date 2018-07-09 16:25
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<UserDTO> result = remoteUserService.queryUserInfo(username);

        if (!Objects.equals(result.getCode(), ResultCode.SUCCESS.code())) {
            throw new UsernameNotFoundException("用户不存在或者密码错误");
        }

        UserDTO userInfo = result.getData();

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("role_admin"));
        return new User(userInfo.getAccount(), "{bcrypt}" + userInfo.getPwd(), grantedAuthorities);
    }


}
