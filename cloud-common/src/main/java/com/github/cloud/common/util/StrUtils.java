package com.github.cloud.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils
 *
 * @author : czk
 * @date 2018-06-28
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
    public static String camelToUnderline(String param) {
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
}
