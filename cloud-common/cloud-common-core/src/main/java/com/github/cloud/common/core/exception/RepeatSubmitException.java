package com.github.cloud.common.core.exception;

/**
 * 重复提交异常
 *
 * @author : czk
 * @date 2018-07-18
 */
public class RepeatSubmitException extends RuntimeException {

    private static final long serialVersionUID = -2602736863286555L;

    public RepeatSubmitException() {
    }

    @Override
    public String getMessage() {
        return "禁止重复提交数据";
    }
}
