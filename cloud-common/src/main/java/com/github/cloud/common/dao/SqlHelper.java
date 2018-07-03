package com.github.cloud.common.dao;

import com.github.cloud.common.domain.entity.Column;
import com.github.cloud.common.util.Assert;
import com.github.cloud.common.util.CollectionUtils;
import com.github.cloud.common.util.ReflectionUtils;
import com.github.cloud.common.util.StrUtils;
import org.springframework.util.ClassUtils;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : czk
 * @date 2018-07-03 09:01
 */
class SqlHelper {

    /**
     * 缓存反射类表字段信息
     */
    private static final Map<String, List<Column>> TABLE_COLUMN_CACHE = new ConcurrentHashMap<>();

    private static final String INSERT_SQL = "insert into {} ({}) values ({})";

    /**
     * 获取Insert Sql
     *
     * @param clazz     类class
     * @param tableName 表名称
     * @param paramSet  sql参数
     * @return 添加sql
     */
    static String getInsertSql(@Nonnull Class<?> clazz, @Nonnull String tableName, @Nonnull Set<String> paramSet) {
        StringBuilder fieldBuilder = new StringBuilder();
        StringBuilder placeholderBuilder = new StringBuilder();
        List<Column> columnList = getColumn(clazz);
        for (Column column : columnList) {
            if (paramSet.contains(column.getProperty())) {
                fieldBuilder.append(",").append(column.getColumn());
                placeholderBuilder.append(",").append(":").append(column.getProperty());
            }
        }
        Assert.isTrue(StrUtils.isNotEmpty(fieldBuilder.toString()), "column can not be empty");
        String field = StrUtils.removeStart(fieldBuilder.toString(), ",");
        String placeholder = StrUtils.removeStart(placeholderBuilder.toString(), ",");
        return StrUtils.format(INSERT_SQL, tableName, field, placeholder);
    }

    /**
     * 获取字段信息
     *
     * @param clazz 类class
     * @return 字段信息
     */
    private static List<Column> getColumn(@Nonnull Class<?> clazz) {
        String key = ClassUtils.getUserClass(clazz).getName();
        if (TABLE_COLUMN_CACHE.containsKey(key)) {
            return TABLE_COLUMN_CACHE.get(key);
        }
        return initColumn(clazz);
    }

    /**
     * 初始化缓存信息
     *
     * @param clazz 类class
     * @return 字段信息
     */
    private synchronized static List<Column> initColumn(@Nonnull Class<?> clazz) {
        String key = ClassUtils.getUserClass(clazz).getName();
        List<Column> tableFieldList = getTableFieldList(clazz);
        TABLE_COLUMN_CACHE.put(key, tableFieldList);
        return tableFieldList;
    }

    /**
     * 获取表字段信息
     *
     * @param clazz 类class
     * @return TableFieldInfoList
     */
    private static List<Column> getTableFieldList(@Nonnull Class<?> clazz) {
        List<Field> fieldList = ReflectionUtils.getFieldList(clazz);
        if (CollectionUtils.isEmpty(fieldList)) {
            throw new RuntimeException("表字段为空");
        }
        List<Column> columnList = new ArrayList<>(fieldList.size());
        if (CollectionUtils.isNotEmpty(fieldList)) {
            for (Field field : fieldList) {
                columnList.add(new Column(field.getName(), StrUtils.camelToUnderline(field.getName())));
            }
        }
        return columnList;
    }
}
