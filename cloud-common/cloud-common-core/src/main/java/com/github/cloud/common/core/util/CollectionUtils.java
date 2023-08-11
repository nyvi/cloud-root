package com.github.cloud.common.core.util;

import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类, 继承org.springframework.util.CollectionUtils
 *
 * @author : huweihua
 * @date 2023-06-28
 */
public final class CollectionUtils extends org.springframework.util.CollectionUtils {

    private CollectionUtils() {
        throw new AssertionError("No CollectionUtils instances for you!");
    }

    /**
     * Return {@code true} if the supplied Collection is not {@code null} and empty.
     * Otherwise, return {@code false}.
     *
     * @param collection the Collection to check
     * @return whether the given Collection is not empty
     */
    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * Return {@code true} if the supplied Map is not {@code null} and empty.
     * Otherwise, return {@code false}.
     *
     * @param map the Map to check
     * @return whether the given Map is not empty
     */
    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return !isEmpty(map);
    }
}
