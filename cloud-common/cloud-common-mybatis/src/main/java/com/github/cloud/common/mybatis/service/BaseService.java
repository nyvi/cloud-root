package com.github.cloud.common.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.cloud.common.mybatis.dto.PageDTO;
import com.github.cloud.common.mybatis.entity.Key;
import com.github.cloud.common.mybatis.request.PageRequest;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author : czk
 * @date 2018-09-27 16:14
 */
public interface BaseService<T extends Key> {

    /**
     * 保存
     *
     * @param entity 实体对象
     * @return 成功返回true, 失败返回false
     */
    boolean save(@Nonnull T entity);

    /**
     * 根据 ID 选择修改非空字段
     *
     * @param entity 实体对象
     * @return 成功返回true, 失败返回false
     */
    boolean update(@Nonnull T entity);

    /**
     * id为null save 否则 update
     *
     * @param entity 实体对象
     * @return 成功返回true, 失败返回false
     */
    boolean saveOrUpdate(@Nonnull T entity);

    /**
     * 批量保存
     *
     * @param saveList 实体对象集合
     * @return 成功返回true, 失败返回false
     */
    boolean batchSave(@Nonnull List<T> saveList);

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     * @return 成功返回true, 失败返回false
     */
    boolean deleteById(@Nonnull Long id);

    /**
     * 根据 entity 条件，删除记录
     *
     * @param queryWrapper 实体包装类
     * @return 成功返回true, 失败返回false
     */
    boolean delete(Wrapper<T> queryWrapper);

    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表
     * @return 成功返回true, 失败返回false
     */
    boolean deleteByIds(@Nonnull Collection<Long> idList);

    /**
     * 根据 ID 查询
     *
     * @param id id
     * @return 实体对象
     */
    Optional<T> getById(@Nonnull Long id);

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表
     * @return 实体对象列表
     */
    @Nonnull
    List<T> listByIds(@Nonnull Collection<Long> idList);

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param queryWrapper 实体对象封装操作类
     * @return 数量
     */
    int count(Wrapper<T> queryWrapper);

    /**
     * 查询列表
     *
     * @param queryWrapper 实体对象封装操作类
     * @return 实体结果
     */
    @Nonnull
    List<T> list(Wrapper<T> queryWrapper);

    /**
     * 分页查询
     *
     * @param query        查询参数
     * @param queryWrapper 查询条件
     * @param <Q>          查询参数类型
     * @return 返回数据
     */
    @Nonnull
    <Q extends PageRequest> PageDTO<T> listPage(@Nonnull Q query, Wrapper<T> queryWrapper);
}
