package com.github.cloud.common.web.util;

import com.github.cloud.common.core.util.Result;

/**
 * @author : czk
 * @date 2018-10-11
 */
@FunctionalInterface
public interface ServiceCallBack<T> {

    /**
     * 执行业务接口
     *
     * @return 返回结果
     * @throws Exception 异常
     */
    Result<T> execute() throws Exception;

}
