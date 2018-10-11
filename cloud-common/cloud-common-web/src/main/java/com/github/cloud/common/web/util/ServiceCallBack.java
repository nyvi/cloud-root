package com.github.cloud.common.web.util;

import com.github.cloud.common.core.util.Result;

/**
 * @author : czk
 * @date 2018-10-11
 */
@FunctionalInterface
public interface ServiceCallBack<T> {

    Result<T> execute() throws Exception;

}
