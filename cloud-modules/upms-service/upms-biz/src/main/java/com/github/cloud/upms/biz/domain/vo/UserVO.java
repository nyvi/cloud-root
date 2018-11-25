package com.github.cloud.upms.biz.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.cloud.upms.biz.domain.entity.SysUserDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : czk
 * @date 2018-10-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"createBy", "gmtCreate", "modifyBy", "gmtModify", "active", "pwd"})
public class UserVO extends SysUserDO {

    private static final long serialVersionUID = -5140105805760411529L;
}
