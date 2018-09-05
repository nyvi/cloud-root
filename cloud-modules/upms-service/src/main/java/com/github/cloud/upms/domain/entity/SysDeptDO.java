package com.github.cloud.upms.domain.entity;

import com.github.cloud.common.core.domain.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门
 *
 * @author : czk
 * @date 2018-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDeptDO extends BaseDO {

    private static final long serialVersionUID = -226244367777312016L;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 上级部门
     */
    private Long parentId;
}
