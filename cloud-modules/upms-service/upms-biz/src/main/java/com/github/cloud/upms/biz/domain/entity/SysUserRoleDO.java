package com.github.cloud.upms.biz.domain.entity;

import lombok.Data;

/**
 * 用户角色
 *
 * @author : czk
 * @date 2018-08-08
 */
@Data
public class SysUserRoleDO {

    private static final long serialVersionUID = -5780806411918212042L;

    /**
     * ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;
}
