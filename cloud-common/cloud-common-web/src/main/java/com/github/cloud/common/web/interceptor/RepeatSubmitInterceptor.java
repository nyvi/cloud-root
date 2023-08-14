package com.github.cloud.common.web.interceptor;

import com.github.cloud.common.core.annotations.RepeatSubmit;
import com.github.cloud.common.core.cache.ICache;
import com.github.cloud.common.core.constant.SysConstant;
import com.github.cloud.common.core.exception.RepeatSubmitException;
import com.github.cloud.common.core.util.StrUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 防重复提交拦截器
 *
 * @author : huweihua
 * @date 2023-07-30
 */
@Slf4j
@Component
@AllArgsConstructor
public class RepeatSubmitInterceptor extends HandlerInterceptorAdapter {

    private final ICache cache;

    private static final String RECOMMIT_KEY = "{}_{}_recommit_{}";

    private static final String KEY = "cloud:repeatSubmit:{}";

    /**
     * 判断参数是否是重复提交
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // Get 请求不拦截
        if (RequestMethod.GET.toString().equals(request.getMethod())) {
            return true;
        }

        // 没有token不拦截
        String authorization = request.getHeader(SysConstant.HEAD_AUTHORIZATION);
        if (StrUtils.isBlank(authorization)) {
            return true;
        }

        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            boolean annotationPresent = method.isAnnotationPresent(RepeatSubmit.class);
            // 验证Json类型的请求
            if (annotationPresent && StrUtils.isNotBlank(request.getContentType()) && StrUtils.containsIgnoreCase(request.getContentType(), SysConstant.CONTENT_TYPE)) {
                try {
                    String param = StrUtils.format(RECOMMIT_KEY, authorization, method.getName(), this.getParamValue(request));
                    String key = StrUtils.format(KEY, DigestUtils.md5Hex(param));
                    int repeatTime = method.getAnnotation(RepeatSubmit.class).value();
                    if (!cache.setNX(key, StrUtils.EMPTY, repeatTime, TimeUnit.SECONDS)) {
                        throw new RepeatSubmitException();
                    }
                } catch (IOException e) {
                    log.error("重复提交异常", e);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 获取请求参数
     *
     * @param request request请求
     * @return 请求参数
     * @throws IOException io异常
     */
    private String getParamValue(HttpServletRequest request) throws IOException {
        byte[] bytes = IOUtils.toByteArray(request.getInputStream());
        return Arrays.toString(bytes);
    }

}

