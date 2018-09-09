package com.github.cloud.common.core.util;

import org.springframework.cglib.beans.BeanCopier;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean 工具类
 *
 * @author : czk
 * @date 2018-09-09
 */
public class BeanUtils {

    /**
     * BeanCopier 缓存
     */
    private static final Map<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>();

    /**
     * 从 source 复制属性(同名且同类型)到 target
     *
     * @param source the source bean
     * @param target the target bean
     */
    public static void copyProperties(@Nonnull Object source, @Nonnull Object target) {
        BeanCopier copier = getBeanCopier(source.getClass(), target.getClass());
        copier.copy(source, target, null);
    }

    /**
     * 从 source List 复制属性(同名且同类型)到 target List
     * 如果 targetList 为null, 则会new一个
     *
     * @param sourceList the source bean List
     * @param targetList the target bean List
     * @param targetType target class Type
     * @param <T>        target class
     */
    public static <T> void copyPropertiesOfList(@Nonnull List<?> sourceList, @Nonnull List<T> targetList, @Nonnull Class<T> targetType) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return;
        }
        T t;
        try {
            for (Object o : sourceList) {
                t = targetType.newInstance();
                copyProperties(o, t);
                targetList.add(t);
            }
        } catch (Exception e) {
            throw new RuntimeException(StrUtils.format("Create new instance of {} failed: {}", targetType, e.getMessage()));
        }
    }

    /**
     * 从 source List 复制属性(同名且同类型)到 target List
     * 如果 targetList 为null, 则会new一个
     *
     * @param sourceList the source bean List
     * @param targetType target class Type
     * @param <T>        target class
     * @return target List
     */
    public static <T> List<T> copyPropertiesOfList(@Nonnull List<?> sourceList, @Nonnull Class<T> targetType) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Collections.emptyList();
        }
        List<T> targetList = new ArrayList<>(sourceList.size());
        copyPropertiesOfList(sourceList, targetList, targetType);
        return targetList;
    }

    /**
     * 获取 BeanCopier对象
     *
     * @param sourceClass the source class
     * @param targetClass the target class
     * @return BeanCopier
     */
    private static BeanCopier getBeanCopier(Class sourceClass, Class targetClass) {
        String beanKey = generateKey(sourceClass, targetClass);
        BeanCopier copier;
        if (BEAN_COPIER_CACHE.containsKey(beanKey)) {
            copier = BEAN_COPIER_CACHE.get(beanKey);
        } else {
            copier = BeanCopier.create(sourceClass, targetClass, false);
            BEAN_COPIER_CACHE.put(beanKey, copier);
        }
        return copier;
    }

    /**
     * 获取缓存key
     *
     * @param sourceClass the source class
     * @param targetClass the target class
     * @return the cache key of BeanCopier
     */
    private static String generateKey(Class<?> sourceClass, Class<?> targetClass) {
        return sourceClass.toString() + targetClass.toString();
    }

}
