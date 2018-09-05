package com.github.cloud.upms.domain.entity;

import com.github.cloud.common.core.domain.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;


/**
 * 用户表
 *
 * @author : czk
 * @date 2018-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserDO extends BaseDO {

    private static final long serialVersionUID = 4767762072434300629L;

    /**
     * 账户
     */
    @NotEmpty
    private String account;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String pwd;
}
