package com.github.cloud.common.mybatis.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.cloud.common.mybatis.dto.PageDTO;
import com.github.cloud.common.mybatis.dto.Pagination;

import javax.annotation.Nonnull;

/**
 * 分页工具类
 *
 * @author : huweihua
 * @date 2023-07-30 14:14
 */
public final class PageHelper {

    /**
     * 分页结果转换
     *
     * @param page mybatis plus 分页对象
     * @param <T>  实体
     * @return 业务分页对象
     */
    @Nonnull
    public static <T> PageDTO<T> convert(@Nonnull IPage<T> page) {
        return PageDTO.build(Pagination.build(page.getCurrent(), page.getSize(), page.getTotal()), page.getRecords());
    }

}
