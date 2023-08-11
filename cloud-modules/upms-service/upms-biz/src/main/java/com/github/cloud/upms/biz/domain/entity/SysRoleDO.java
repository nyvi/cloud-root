package com.github.cloud.upms.biz.domain.entity;

import com.github.cloud.common.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色表
 *
 * @author : huweihua
 * @date 2023-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDO extends BaseDO {

    private static final long serialVersionUID = 1958826455576019513L;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;
}
