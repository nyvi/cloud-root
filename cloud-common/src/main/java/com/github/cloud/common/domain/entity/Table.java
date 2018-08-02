package com.github.cloud.common.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author : czk
 * @date 2018-08-02
 */
@Data
@AllArgsConstructor
public class Table {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 字段名称
     */
    private List<Column> columnList;
}
