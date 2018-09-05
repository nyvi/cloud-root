package com.github.cloud.common.core.util;

/**
 * 业务断言, 满足业务条件的方可继续进行后续业务
 *
 * @author : czk
 * @date 2018-07-03 09:42
 */
public class Assert {

    /**
     * 断言方法
     *
     * @param expression a boolean expression
     * @param message    异常信息
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new RuntimeException(message);
        }
    }

    /**
     * 断言方法
     *
     * @param expression a boolean expression
     * @param message    异常信息，允许使用标准的{}占位符用于表示占位信息
     * @param args       异常信息中的占位参数
     */
    public static void isTrue(boolean expression, String message, Object... args) {
        if (!expression) {
            throw new RuntimeException(StrUtils.format(message, args));
        }
    }
}
