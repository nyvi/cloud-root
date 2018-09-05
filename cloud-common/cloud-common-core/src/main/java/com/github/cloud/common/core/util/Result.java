package com.github.cloud.common.core.util;

import com.github.cloud.common.core.constant.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * API响应结果
 *
 * @author : czk
 * @date 2018-07-11
 */
@Data
@AllArgsConstructor
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.code(), "success", data);
    }

    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(ResultCode.ERROR.code(), msg, data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(ResultCode.ERROR.code(), msg, null);
    }

    public static <T> Result<T> failed(ResultCode code, String msg) {
        return new Result<>(code.code(), msg, null);
    }
}
