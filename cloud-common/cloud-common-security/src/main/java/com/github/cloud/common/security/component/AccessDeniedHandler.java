package com.github.cloud.common.security.component;

import com.alibaba.fastjson.JSONObject;
import com.github.cloud.common.core.constant.SysConstant;
import com.github.cloud.common.core.constant.enums.ResultCode;
import com.github.cloud.common.core.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 重写授权失败(OAuth2AccessDeniedHandler)
 *
 * @author : czk
 * @date 2018-09-06
 */
@Slf4j
@Component
public class AccessDeniedHandler extends OAuth2AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException)
            throws IOException {
        log.info("授权失败，禁止访问 {}", request.getRequestURI());
        response.setCharacterEncoding(SysConstant.UTF8);
        response.setContentType(SysConstant.CONTENT_TYPE);
        response.setStatus(HttpStatus.OK.value());
        Result result = Result.failed(ResultCode.NO_AUTHORITY, "您没有权限, 禁止访问");
        PrintWriter printWriter = response.getWriter();
        printWriter.append(JSONObject.toJSONString(result));
    }
}
