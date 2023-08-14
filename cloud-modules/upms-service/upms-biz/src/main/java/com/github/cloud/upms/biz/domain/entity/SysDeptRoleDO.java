package com.github.cloud.upms.biz.domain.entity;


import com.github.cloud.common.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门角色
 *
 * @author : huweihua
 * @date 2023-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDeptRoleDO extends BaseDO {

    private static final long serialVersionUID = 5184405797519368270L;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 角色id
     */
    private Long roleId;
}
