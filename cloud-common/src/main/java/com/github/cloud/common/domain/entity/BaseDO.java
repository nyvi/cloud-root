package com.github.cloud.common.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * BaseDO 定义数据库公共字段
 *
 * @author : czk
 * @date 2018-07-02
 */
@Data
public class BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改人
     */
    private Long modifyBy;
    /**
     * 修改时间
     */
    private Date gmtModify;
    /**
     * 是否有效 0-无效 1-有效
     */
    private Integer active;
}
