package com.github.cloud.common.core.util;

import com.google.common.collect.Maps;
import org.springframework.cglib.beans.BeanMap;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;

/**
 * beanMap 互转工具类
 *
 * @author : czk
 * @date 2018-07-02
 */
public final class BeanMapUtils {

    private BeanMapUtils() {
        throw new AssertionError("No BeanMapUtils instances for you!");
    }

    /**
     * bean转为Map,忽略NULL字段
     *
     * @param bean 实体bean
     * @param <T>  泛型
     * @return map对象
     */
    public static <T> Map<String, Object> beanToMap(@Nonnull T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(beanMap.size());
        for (Object key : beanMap.keySet()) {
            Object value = beanMap.get(key);
            if (Objects.nonNull(value)) {
                map.put(key.toString(), value);
            }
        }
        return map;
    }

    /**
     * Map转Bean
     *
     * @param map  待转换Map
     * @param bean 实体
     * @param <T>  泛型
     * @return bean
     */
    public static <T> T mapToBean(@Nonnull Map<String, Object> map, @Nonnull T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
}
