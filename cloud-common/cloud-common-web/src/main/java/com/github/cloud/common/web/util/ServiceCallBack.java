package com.github.cloud.common.web.util;

import com.github.cloud.common.core.util.Result;

/**
 * @author : huweihua
 * @date 2023-07-11
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
