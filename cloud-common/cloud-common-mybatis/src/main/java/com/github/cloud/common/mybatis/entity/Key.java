package com.github.cloud.common.mybatis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据库主键
 *
 * @author : czk
 * @date 2018-09-27
 */
@Data
public class Key implements Serializable {

    private static final long serialVersionUID = -3244514399020168211L;

    /**
     * id
     */
    private Long id;

}
