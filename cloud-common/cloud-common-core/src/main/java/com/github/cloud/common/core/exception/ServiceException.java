package com.github.cloud.common.core.exception;

/**
 * 自定义服务异常
 *
 * @author : huweihua
 * @date 2023-07-18
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 311018416192283941L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
