package com.github.cloud.common.core.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author : czk
 * @date 2018-09-29 17:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageRequest extends BaseRequest {

    private static final long serialVersionUID = -4926338044590990037L;

    /**
     * 当前页码
     */
    private Integer currentPage;

    /**
     * 每页显示个数
     */
    private Integer pageSize;

    /**
     * 排序 ASC
     */
    private List<String> ascList;

    /**
     * 排序 DESC
     */
    private List<String> descList;
}
