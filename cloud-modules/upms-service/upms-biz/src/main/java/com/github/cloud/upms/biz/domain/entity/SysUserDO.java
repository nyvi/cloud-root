package com.github.cloud.upms.biz.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.cloud.common.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户表
 *
 * @author : huweihua
 * @date 2023-07-03
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
