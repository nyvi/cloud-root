package com.github.cloud.auth.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final String user1 = "admin";
        final String user2 = "user";
        System.out.println(username);
        if (!Objects.equals(username, user1) && !Objects.equals(username, user2)) {
            System.out.println("???");
            throw new UsernameNotFoundException("can not find " + username);
        }
        String pwd = new BCryptPasswordEncoder().encode("123");
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("role_admin"));
        return new User(username, pwd, grantedAuthorities);
    }


}
