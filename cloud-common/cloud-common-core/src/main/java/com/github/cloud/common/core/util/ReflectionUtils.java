package com.github.cloud.common.core.util;

import com.google.common.collect.Sets;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 反射工具类
 *
 * @author : czk
 * @date 2018-07-02
 */
public final class ReflectionUtils {

    private ReflectionUtils() {
        throw new AssertionError("No ReflectionUtils instances for you!");
    }

    /**
     * 获取该类的所有属性列表
     *
     * @param clazz 类class
     * @return 属性列表
     */
    public static List<Field> getFieldList(@Nonnull Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<Field> fieldList = new LinkedList<>();
        for (Field field : fields) {
            // 过滤静态属性
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            // 过滤 transient关键字修饰的属性
            if (Modifier.isTransient(field.getModifiers())) {
                continue;
            }
            fieldList.add(field);
        }
        /* 处理父类字段 */
        Class<?> superClass = clazz.getSuperclass();
        if (superClass.equals(Object.class)) {
            return fieldList;
        }
        /* 排除重载属性 */
        return excludeOverrideSuperField(fieldList, getFieldList(superClass));
    }

    /**
     * 排序重置父类属性
     *
     * @param fieldList      子类属性
     * @param superFieldList 父类属性
     * @return 去重后属性
     */
    private static List<Field> excludeOverrideSuperField(@Nonnull List<Field> fieldList, @Nonnull List<Field> superFieldList) {
        // 子类属性
        Set<String> fieldSet = Sets.newHashSetWithExpectedSize(fieldList.size() + superFieldList.size());
        for (Field field : fieldList) {
            fieldSet.add(field.getName());
        }
        for (Field superField : superFieldList) {
            if (!fieldSet.contains(superField.getName())) {
                // 不包含加入属性列表
                fieldList.add(superField);
            }
        }
        return fieldList;
    }
}
