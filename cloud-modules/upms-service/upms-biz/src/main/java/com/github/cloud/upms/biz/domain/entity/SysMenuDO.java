package com.github.cloud.upms.biz.domain.entity;

import com.github.cloud.common.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单表
 *
 * @author : huweihua
 * @date 2023-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysMenuDO extends BaseDO {

    private static final long serialVersionUID = -6646345383673098844L;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * URI
     */
    private String uri;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型:1-目录,2-菜单,3-按钮
     */
    private Integer type;

    /**
     * 权限码
     */
    private String permissions;

    /**
     * 上级ID
     */
    private Long parentId;
}
