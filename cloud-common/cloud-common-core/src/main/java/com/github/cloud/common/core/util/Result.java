package com.github.cloud.common.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.cloud.common.core.constant.enums.ResultCode;
import com.github.cloud.common.core.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * API响应结果
 *
 * @author : huweihua
 * @date 2023-07-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS.code(), "success", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.code(), "success", data);
    }

    public static <T> Result<T> error() {
        return new Result<>(ResultCode.ERROR.code(), "操作失败", null);
    }

    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(ResultCode.ERROR.code(), msg, data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(ResultCode.ERROR.code(), msg, null);
    }

    public static <T> Result<T> error(ServiceException exception) {
        return new Result<>(ResultCode.ERROR.code(), exception.getMessage(), null);
    }

    public static <T> Result<T> failed(ResultCode code, String msg) {
        return new Result<>(code.code(), msg, null);
    }
}
