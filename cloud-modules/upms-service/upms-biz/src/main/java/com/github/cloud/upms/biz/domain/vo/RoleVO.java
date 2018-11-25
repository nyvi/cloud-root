package com.github.cloud.upms.biz.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.cloud.upms.biz.domain.entity.SysRoleDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Caixin
 * @date 2018/10/18 13:56
 */

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"createBy", "gmtCreate", "modifyBy", "gmtModify", "active", "versionNo"})
public class RoleVO extends SysRoleDO {

    private static final long serialVersionUID = -7957828799743462324L;
}
