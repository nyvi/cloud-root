package com.github.cloud.common.dao;

import com.github.cloud.common.domain.entity.Column;
import com.github.cloud.common.domain.entity.Table;
import com.github.cloud.common.util.Assert;
import com.github.cloud.common.util.CollectionUtils;
import com.github.cloud.common.util.ReflectionUtils;
import com.github.cloud.common.util.StrUtils;
import org.springframework.util.ClassUtils;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author : czk
 * @date 2018-07-03 09:01
 */
class SqlHelper {

    /**
     * 缓存反射类信息
     */
    private static final Map<String, Table> TABLE_CACHE = new ConcurrentHashMap<>();

    private static final String INSERT_SQL = "insert into {} ({}) values ({})";

    private static final String SELECT_SQL = "select {} from {} where id=:id and active = 1";

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
        Table table = getTable(clazz);
        table.getColumnList().stream().filter(f -> paramSet.contains(f.getProperty())).forEach(r -> {
            fieldBuilder.append(",").append(r.getColumn());
            placeholderBuilder.append(",").append(":").append(r.getProperty());
        });
        Assert.isTrue(StrUtils.isNotEmpty(fieldBuilder.toString()), "column can not be empty");
        String field = StrUtils.removeStart(fieldBuilder.toString(), ",");
        String placeholder = StrUtils.removeStart(placeholderBuilder.toString(), ",");
        return StrUtils.format(INSERT_SQL, StrUtils.defaultIfBlank(tableName, table.getTableName()), field, placeholder);
    }

    /**
     * 获取根据id查询Sql
     *
     * @param clazz     类class
     * @param tableName 表名称
     * @return 根据id查询sql
     */
    static String getEntitySql(@Nonnull Class<?> clazz, @Nonnull String tableName) {
        StringBuilder fieldBuilder = new StringBuilder();
        Table table = getTable(clazz);
        for (Column column : table.getColumnList()) {
            // 忽略逻辑字段
            if ("active".equals(column.getColumn())) {
                continue;
            }
            fieldBuilder.append(",").append(column.getColumn());
        }
        Assert.isTrue(StrUtils.isNotEmpty(fieldBuilder.toString()), "column can not be empty");
        String field = StrUtils.removeStart(fieldBuilder.toString(), ",");
        return StrUtils.format(SELECT_SQL, field, StrUtils.defaultIfBlank(tableName, table.getTableName()));
    }

    /**
     * 获取表信息
     *
     * @param clazz 类class
     * @return 字段信息
     */
    private static Table getTable(@Nonnull Class<?> clazz) {
        String key = ClassUtils.getUserClass(clazz).getName();
        return TABLE_CACHE.containsKey(key) ? TABLE_CACHE.get(key) : initTable(clazz);
    }

    /**
     * 初始化Table缓存信息
     *
     * @param clazz 类class
     * @return 表信息
     */
    private synchronized static Table initTable(@Nonnull Class<?> clazz) {
        Class<?> userClass = ClassUtils.getUserClass(clazz);
        String tableName = StrUtils.camelToUnderline(userClass.getSimpleName());
        Table table = new Table(tableName, getTableFieldList(clazz));
        TABLE_CACHE.put(userClass.getName(), table);
        return table;
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
        return fieldList.stream().map(r -> new Column(r.getName(), StrUtils.camelToUnderline(r.getName()))).collect(Collectors.toList());
    }
}
