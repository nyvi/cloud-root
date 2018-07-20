package com.github.cloud.common.exception;

import com.github.cloud.common.constant.enums.ResultCode;
import com.github.cloud.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常
 *
 * @author : czk
 * @date 2018-07-18
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handle(Exception exception) {
        // 打印异常日志
        log.error(exception.getMessage(), exception);
        // 异常类型处理
        if (exception instanceof ServiceException) {
            return Result.error(exception.getMessage());
        } else if (exception instanceof NoHandlerFoundException) {
            return Result.failed(ResultCode.NO_FOUND, exception.getMessage());
        } else if (exception instanceof RepeatedSubmitException) {
            return Result.failed(ResultCode.REPEATED_SUBMIT, exception.getMessage());
        }
        return Result.error(exception.getMessage());
    }
}
