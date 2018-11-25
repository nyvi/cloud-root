package com.github.cloud.common.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.github.cloud.common.mybatis.dto.PageDTO;
import com.github.cloud.common.mybatis.entity.Key;
import com.github.cloud.common.mybatis.request.PageRequest;
import com.github.cloud.common.mybatis.service.BaseService;
import com.github.cloud.common.mybatis.util.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author : czk
 * @date 2018-09-27 16:18
 */
@SuppressWarnings({"unchecked", "SpringJavaAutowiredMembersInspection"})
public class BaseServiceImpl<M extends BaseMapper<T>, T extends Key> implements BaseService<T> {

    @Autowired
    protected M baseMapper;

    @Override
    public boolean save(@Nonnull T entity) {
        return retBool(baseMapper.insert(entity));
    }

    @Override
    public boolean update(@Nonnull T entity) {
        return retBool(baseMapper.updateById(entity));
    }

    @Override
    public boolean saveOrUpdate(@Nonnull T entity) {
        return entity.getId() == null ? save(entity) : update(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchSave(@Nonnull List<T> saveList) {
        // 每30条刷新一次
        final int batchSize = 30;
        SqlSession batchSqlSession = sqlSessionBatch();
        String sqlStatement = sqlStatement();
        try {
            int i = 0;
            for (T entity : saveList) {
                batchSqlSession.insert(sqlStatement, entity);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        } finally {
            closeSqlSession(batchSqlSession);
        }
        return true;
    }

    @Override
    public boolean deleteById(@Nonnull Long id) {
        return SqlHelper.delBool(baseMapper.deleteById(id));
    }

    @Override
    public boolean delete(Wrapper<T> queryWrapper) {
        return SqlHelper.delBool(baseMapper.delete(queryWrapper));
    }

    @Override
    public boolean deleteByIds(@Nonnull Collection<Long> idList) {
        return SqlHelper.delBool(baseMapper.deleteBatchIds(idList));
    }

    @Override
    public Optional<T> getById(@Nonnull Long id) {
        return Optional.ofNullable(baseMapper.selectById(id));
    }

    @Override
    @Nonnull
    public List<T> listByIds(@Nonnull Collection<Long> idList) {
        return baseMapper.selectBatchIds(idList);
    }

    @Override
    public int count(Wrapper<T> queryWrapper) {
        return SqlHelper.retCount(baseMapper.selectCount(queryWrapper));
    }

    @Nonnull
    @Override
    public List<T> list(Wrapper<T> queryWrapper) {
        return baseMapper.selectList(queryWrapper);
    }

    @Nonnull
    @Override
    public <Q extends PageRequest> PageDTO<T> listPage(@Nonnull Q query, Wrapper<T> queryWrapper) {
        return PageHelper.convert(baseMapper.selectPage(query.convertFor(), queryWrapper));
    }

    /**
     * <p>
     * 判断数据库操作是否成功
     * </p>
     *
     * @param result 数据库操作返回影响条数
     * @return boolean
     */
    private boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    private Class<T> currentModelClass() {
        return ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    /**
     * <p>
     * 批量操作 SqlSession
     * </p>
     */
    private SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(currentModelClass());
    }

    /**
     * 释放sqlSession
     *
     * @param sqlSession session
     */
    private void closeSqlSession(SqlSession sqlSession) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(currentModelClass()));
    }

    /**
     * 获取SqlStatement
     *
     * @return sql语句
     */
    private String sqlStatement() {
        return SqlHelper.table(currentModelClass()).getSqlStatement(SqlMethod.INSERT_ONE.getMethod());
    }

}
