package com.github.cloud.common.web.util;

import com.github.cloud.common.core.constant.enums.ResultCode;
import com.github.cloud.common.core.exception.ServiceException;
import com.github.cloud.common.core.request.BaseRequest;
import com.github.cloud.common.core.util.CollectionUtils;
import com.github.cloud.common.core.util.Result;
import com.github.cloud.common.core.util.StrUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 统一验证服务请求参数
 *
 * @author : huweihua
 * @date 2023-07-11
 */
@Slf4j
public class ServiceHelper {

    private static ValidatorFactory globalValidator = Validation.buildDefaultValidatorFactory();


    /**
     * 验证请求参数
     *
     * @param v       请求参数
     * @param action  执行服务方法
     * @param classes 组别
     * @param <V>     请求类型
     * @return 返回结果
     */
    public static <V extends BaseRequest, T> Result<T> execute(@NonNull V v, @NonNull ServiceCallBack<T> action, Class<?>... classes) {
        // 执行参数验证
        String resultMsg = validation(v, classes);
        if (StrUtils.isNotBlank(resultMsg)) {
            return Result.failed(ResultCode.PARAMETER_VALIDATOR_ERROR, resultMsg);
        }
        try {
            // 执行方法
            return action.execute();
        } catch (Exception ex) {
            if (ex instanceof ServiceException) {
                return Result.failed(ResultCode.ERROR, ex.getMessage());
            } else {
                log.error("系统异常:", ex);
            }
            return Result.error();
        }
    }

    /**
     * 验证请求参数
     *
     * @param v       请求参数
     * @param classes 组别
     * @param <V>     请求类型
     * @return 错误信息
     */
    @Nullable
    public static <V extends BaseRequest> String validation(@NonNull V v, Class<?>... classes) {
        Validator validator = globalValidator.getValidator();
        Set<ConstraintViolation<V>> set = validator.validate(v, classes);

        if (CollectionUtils.isEmpty(set)) {
            return null;
        }

        StringBuilder errorMsg = new StringBuilder();
        errorMsg.append("[");

        for (ConstraintViolation<V> cv : set) {
            errorMsg.append("{");
            errorMsg.append("\"key\":\"").append(StrUtils.defaultIfBlank(cv.getPropertyPath().toString(), "提示")).append("\",");
            errorMsg.append("\"value\":\"").append(cv.getMessage()).append("\"");
            errorMsg.append("},");
        }

        errorMsg.deleteCharAt(errorMsg.length() - 1);
        errorMsg.append("]");

        return errorMsg.toString();
    }
}
