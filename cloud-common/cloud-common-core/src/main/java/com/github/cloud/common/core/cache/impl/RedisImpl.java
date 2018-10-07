package com.github.cloud.common.core.cache.impl;

import com.github.cloud.common.core.cache.ICache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis 实现
 *
 * @author : czk
 * @date 2018-09-30
 */
@Component
public class RedisImpl implements ICache {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean setNX(String key, String value, long timeout, TimeUnit timeUnit) {
        Boolean flag = redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.setNX((key).getBytes(), value.getBytes()));
        return (flag != null && flag) && setExpire(key, timeout, timeUnit);
    }

    private boolean setExpire(String key, long timeout, TimeUnit timeUnit) {
        Boolean expire = redisTemplate.expire(key, timeout, timeUnit);
        return expire != null && expire;
    }
}
