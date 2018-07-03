package com.github.cloud.upms.domain.entity;

import com.github.cloud.common.domain.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author : czk
 * @date 2018-07-03 11:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDO extends BaseDO {

    private static final long serialVersionUID = 4767762072434300629L;

    /**
     * 账户
     */
    private String account;

    /**
     * 密码
     */
    private String pwd;
}
