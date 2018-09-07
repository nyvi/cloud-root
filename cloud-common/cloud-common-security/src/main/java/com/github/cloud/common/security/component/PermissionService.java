package com.github.cloud.common.security.component;

import org.springframework.stereotype.Component;

/**
 * @author : czk
 * @date 2018-09-07
 */
@Component("pms")
public class PermissionService {

    /**
     * 判断接口是否有权限
     *
     * @param permission 权限码
     * @return 有权限返回true, 没有返回false
     */
    public boolean hasPermission(String permission) {
        System.out.println(permission);
        return true;
    }
}
