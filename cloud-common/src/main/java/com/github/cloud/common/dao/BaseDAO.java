package com.github.cloud.common.dao;

import com.github.cloud.common.domain.entity.BaseDO;
import com.github.cloud.common.util.Assert;
import com.github.cloud.common.util.BeanMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
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
     * @param t         实体
     * @param tableName 表名
     * @param <T>       泛型
     * @return id
     */
    @Nonnull
    public <T extends BaseDO> Long insert(@Nonnull T t, @Nonnull String tableName) {
        Assert.isTrue(Objects.nonNull(t.getId()), "{} id not null", tableName);
        Map<String, Object> paramMap = BeanMapUtils.beanToMap(t);
        String sql = SqlHelper.getInsertSql(t.getClass(), tableName, paramMap.keySet());
        int ret = namedJdbcTemplate.update(sql, paramMap);
        Assert.isTrue(ret > 0, "{} insert error", tableName);
        return t.getId();
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
        Assert.isTrue(ret > 0, "{} insert error", tableName);
        Number id = key.getKey();
        Assert.isTrue(Objects.nonNull(id), "{} auto id failed", tableName);
        t.setId(id.longValue());
        return t.getId();
    }

}
