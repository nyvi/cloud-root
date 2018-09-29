package com.github.cloud.common.mybatis.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页参数
 *
 * @author : czk
 * @date 2018-09-29 17:29
 */
@Data
public class PageDTO<T> implements Serializable {

    private static final long serialVersionUID = 406205322526156728L;

    /**
     * 显示数据
     */
    private List<T> list;

    /**
     * 分页数据
     */
    private Pagination pagination;

    private PageDTO(Pagination pagination, List<T> list) {
        super();
        this.list = list;
        this.pagination = pagination;
    }

    /**
     * 客户端分页返回数据
     *
     * @param pagination 分页参数
     * @param list       显示数据
     * @param <T>        实体对象
     * @return 分页数据
     */
    public static <T> PageDTO<T> build(Pagination pagination, List<T> list) {
        return new PageDTO<>(pagination, list);
    }
}
