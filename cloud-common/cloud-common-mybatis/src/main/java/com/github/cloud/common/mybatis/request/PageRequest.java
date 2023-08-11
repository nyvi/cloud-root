package com.github.cloud.common.mybatis.request;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.cloud.common.core.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author : huweihua
 * @date 2023-07-11 17:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageRequest extends BaseRequest {

    private static final long serialVersionUID = -4926338044590990037L;

    /**
     * 当前页码
     */
    private long currentPage = 1;

    /**
     * 每页显示个数
     */
    private long pageSize = 10;

    /**
     * 排序 ASC
     */
    private List<String> ascList;

    /**
     * 排序 DESC
     */
    private List<String> descList;

    /**
     * 分页查询参数转换
     *
     * @return mybatis plus 分页查询对象
     */
    public <T> Page<T> convertFor() {
        Page<T> page = new Page<>(getCurrentPage(), getPageSize());
        page.setAscs(getAscList());
        page.setDescs(getDescList());
        return page;
    }
}
