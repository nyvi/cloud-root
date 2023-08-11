package com.github.cloud.common.core.cache;

import java.util.concurrent.TimeUnit;

/**
 * 缓存接口
 *
 * @author : huweihua
 * @date 2023-07-04
 */
public interface ICache {

    /**
     * redis提供的setNX函数
     * <br/>
     * 将 key 的值设为 value, 当且仅当 key 不存在
     * 若给定的 key 已经存在, 则 setNX 不做任何动作。
     * setNX 是SET IF NOT EXISTS的简写。
     *
     * @param key      键名称，需要唯一
     * @param value    值
     * @param timeout  超时时长
     * @param timeUnit 超时时长单位
     * @return 成功返回true, 失败返回false
     */
    boolean setNX(String key, String value, long timeout, TimeUnit timeUnit);


}
