package com.github.cloud.common.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防重复提交
 *
 * @author : huweihua
 * @date 2023-07-04
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmit {

    /**
     * 不能重复时间, 单位秒
     * <br/>
     * 默认5秒
     */
    int value() default 5;
}
