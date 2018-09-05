package com.github.cloud.common.core.exception;

/**
 * 自定义服务异常
 *
 * @author : czk
 * @date 2018-07-18
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 311018416192283941L;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
