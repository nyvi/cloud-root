package com.github.cloud.upms.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.cloud.common.core.domain.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户表
 *
 * @author : czk
 * @date 2018-07-03
 */
@Data
@TableName("sys_user")
@EqualsAndHashCode(callSuper = true)
public class SysUserDO extends BaseDO {

    private static final long serialVersionUID = 4767762072434300629L;

    /**
     * 账户
     */
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
