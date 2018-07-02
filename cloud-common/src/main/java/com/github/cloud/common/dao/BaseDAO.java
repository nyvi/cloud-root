package com.github.cloud.common.dao;

import com.github.cloud.common.domain.entity.BaseDO;
import com.github.cloud.common.domain.entity.Column;
import com.github.cloud.common.util.BeanMapUtils;
import com.github.cloud.common.util.CollectionUtils;
import com.github.cloud.common.util.ReflectionUtils;
import com.github.cloud.common.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ClassUtils;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : czk
 * @date 2018-07-02
 */
@Repository
public class BaseDAO {

    private static final String INSERT_SQL = "insert into %s (%s) values (%s)";
    private static final Long FIRST_VERSION_NO = 1L;
    private static final Integer ACTIVE = 1;
    /**
     * 缓存反射类表字段信息
     */
    private static final Map<String, List<Column>> TABLE_COLUMN_CACHE = new ConcurrentHashMap<>();
    private final NamedParameterJdbcTemplate namedJdbcTemplate;


    @Autowired
    public BaseDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * 获取字段信息
     *
     * @param clazz 类class
     * @return 字段信息
     */
    private static List<Column> getColumn(Class<?> clazz) {
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

    public <T extends BaseDO> Long insert(@Nonnull T t, @Nonnull String tableName) {
        Date date = new Date();
        t.setGmtCreate(date);
        t.setGmtModify(date);
        t.setActive(ACTIVE);
        t.setVersionNo(FIRST_VERSION_NO);
        StringBuilder fieldBuilder = new StringBuilder();
        StringBuilder placeholderBuilder = new StringBuilder();
        Map<String, Object> paramMap = BeanMapUtils.beanToMap(t);
        List<Column> columnList = getColumn(t.getClass());
        for (Column column : columnList) {
            if (paramMap.containsKey(column.getProperty())) {
                fieldBuilder.append(",").append(column.getColumn());
                placeholderBuilder.append(",").append(":").append(column.getProperty());
            }
        }
        String sql = String.format(INSERT_SQL, tableName, StrUtils.removeStart(fieldBuilder.toString(), ","),
                StrUtils.removeStart(placeholderBuilder.toString(), ","));
        if (Objects.nonNull(t.getId())) {
            return 0L;
        }
        namedJdbcTemplate.update(sql, paramMap);
        return t.getId();
    }

}
