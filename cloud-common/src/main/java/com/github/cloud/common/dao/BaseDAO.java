package com.github.cloud.common.dao;

import com.github.cloud.common.domain.entity.BaseDO;
import com.github.cloud.common.util.Assert;
import com.github.cloud.common.util.BeanMapUtils;
import com.github.cloud.common.util.CollectionUtils;
import com.github.cloud.common.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * BaseDAO
 *
 * @author : czk
 * @date 2018-07-02
 */
@Repository
public class BaseDAO {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public BaseDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * 添加方法,id自定义
     *
     * @param t   实体
     * @param <T> 泛型
     * @return id
     */
    @Nonnull
    public <T extends BaseDO> Long insert(@Nonnull T t) {
        return insert(t, StrUtils.EMPTY);
    }

    /**
     * 添加方法,id自定义
     *
     * @param t         实体
     * @param tableName 表名
     * @param <T>       泛型
     * @return id
     */
    @Nonnull
    public <T extends BaseDO> Long insert(@Nonnull T t, @Nonnull String tableName) {
        Assert.isTrue(Objects.nonNull(t.getId()), "id not null");
        Map<String, Object> paramMap = BeanMapUtils.beanToMap(t);
        String sql = SqlHelper.getInsertSql(t.getClass(), tableName, paramMap.keySet());
        int ret = namedJdbcTemplate.update(sql, paramMap);
        Assert.isTrue(ret > 0, "insert error");
        return t.getId();
    }

    /**
     * 添加方法,id数据库自增
     *
     * @param t   实体
     * @param <T> 泛型
     * @return id
     */
    @Nonnull
    public <T extends BaseDO> Long insertAutoGenKey(@Nonnull T t) {
        return insertAutoGenKey(t, StrUtils.EMPTY);
    }

    /**
     * 添加方法,id数据库自增
     *
     * @param t         实体
     * @param tableName 表名
     * @param <T>       泛型
     * @return id
     */
    @Nonnull
    public <T extends BaseDO> Long insertAutoGenKey(@Nonnull T t, @Nonnull String tableName) {
        t.setId(null);
        Map<String, Object> paramMap = BeanMapUtils.beanToMap(t);
        String sql = SqlHelper.getInsertSql(t.getClass(), tableName, paramMap.keySet());
        KeyHolder key = new GeneratedKeyHolder();
        int ret = namedJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t), key);
        Assert.isTrue(ret > 0, "insert error");
        Number id = key.getKey();
        Assert.isTrue(Objects.nonNull(id), "auto id failed");
        t.setId(id.longValue());
        return t.getId();
    }

    /**
     * 批量保存
     *
     * @param list 待保存list
     * @param <T>  泛型
     * @return 影响行数
     */
    public <T extends BaseDO> int batchInsert(@Nonnull List<T> list) {
        return batchInsert(list, StrUtils.EMPTY);
    }

    /**
     * 批量保存
     *
     * @param list      待保存list
     * @param tableName 表名
     * @param <T>       泛型
     * @return 影响行数
     */
    public <T extends BaseDO> int batchInsert(@Nonnull List<T> list, @Nonnull String tableName) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        T t = list.get(0);
        Map<String, Object> paramMap = BeanMapUtils.beanToMap(t);
        SqlParameterSource[] valueParameter = SqlParameterSourceUtils.createBatch(list);
        String sql = SqlHelper.getInsertSql(t.getClass(), tableName, paramMap.keySet());
        return namedJdbcTemplate.batchUpdate(sql, valueParameter).length;
    }

    /**
     * 查询实体
     *
     * @param clazz 类class
     * @param id    id
     * @param <T>   泛型
     * @return DO or null
     */
    @Nullable
    public <T extends BaseDO> T getEntity(@Nonnull Class<T> clazz, @Nonnull Long id) {
        return getEntity(clazz, StrUtils.EMPTY, id);
    }

    /**
     * 查询实体
     *
     * @param clazz     类class
     * @param tableName 表名称
     * @param id        id
     * @param <T>       泛型
     * @return DO or null
     */
    @Nullable
    public <T extends BaseDO> T getEntity(@Nonnull Class<T> clazz, @Nonnull String tableName, @Nonnull Long id) {
        String sql = SqlHelper.getEntitySql(clazz, tableName);
        Map<String, Object> paramMap = Collections.singletonMap("id", id);
        RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(clazz);
        List<T> list = namedJdbcTemplate.query(sql, paramMap, rowMapper);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
