package com.github.cloud.upms.domain.entity;

import com.github.cloud.common.core.domain.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色
 *
 * @author : czk
 * @date 2018-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserRoleDO extends BaseDO {

    private static final long serialVersionUID = -5780806411918212042L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;
}
