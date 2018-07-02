package com.github.cloud.common.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 数据库字段
 *
 * @author : czk
 * @date 2018-07-02
 */
@Data
@AllArgsConstructor
public class Column {

    /**
     * 属性名
     */
    private String property;

    /**
     * 字段名
     */
    private String column;
}

