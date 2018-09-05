package com.github.cloud.common.core.constant.enums;

/**
 * 服务状态枚举
 *
 * @author : czk
 * @date 2018-07-11
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 接口异常
     */
    ERROR(400),
    /**
     * 参数验证错误
     */
    PARAMETER_VALIDATOR_ERROR(401),
    /**
     * 没有登录
     */
    NOT_LOGIN_ERROR(402),
    /**
     * 无权限
     */
    NO_AUTHORITY(403),
    /**
     * 找到接口
     */
    NO_FOUND(404),
    /**
     * 重复提交
     */
    REPEATED_SUBMIT(405),
    /**
     * 参数格式有误
     */
    PARAMETER_FORMAT(406);

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

}
