package com.github.cloud.common.core.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils
 *
 * @author : huweihua
 * @date 2023-06-28
 */
public final class StrUtils extends StringUtils {

    /**
     * 下划线字符
     */
    private static final char UNDERLINE = '_';

    private StrUtils() {
        throw new AssertionError("No StrUtils instances for you!");
    }

    /**
     * <p>驼峰式转下划线式字符串(小写)</p>
     *
     * <pre>
     * StrUtils.camelToUnderline("") = ""
     * StrUtils.camelToUnderline(" ") = ""
     * StrUtils.camelToUnderline(NULL) = ""
     * StrUtils.camelToUnderline("userName") = "user_name"
     * </pre>
     *
     * @param param 串驼峰式字符串
     * @return 下划线式字符串(小写)
     */
    public static String camelToUnderline(@Nullable String param) {
        if (isBlank(param)) {
            return EMPTY;
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                sb.append(StrUtils.UNDERLINE);
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * <p>格式化一个带有占位符的字符串</p>
     *
     * <pre>
     * StrUtils.format("123{}",4) = "1234"
     * </pre>
     *
     * @param message 原始字符串,占位符通过{}标记
     * @param args    占位符替换值
     * @return 格式化后的字符串
     */
    public static String format(@Nonnull String message, Object... args) {
        return MessageFormatter.arrayFormat(message, args).getMessage();
    }
}
