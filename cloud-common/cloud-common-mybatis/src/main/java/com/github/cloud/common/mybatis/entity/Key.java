package com.github.cloud.common.mybatis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据库主键
 *
 * @author : huweihua
 * @date 2023-07-27
 */
@Data
public class Key implements Serializable {

    private static final long serialVersionUID = -3244514399020168211L;

    /**
     * id
     */
    private Long id;

}
