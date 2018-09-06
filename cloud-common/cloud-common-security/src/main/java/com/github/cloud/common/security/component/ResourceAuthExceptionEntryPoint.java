package com.github.cloud.common.security.component;

import com.alibaba.fastjson.JSONObject;
import com.github.cloud.common.core.constant.SysConstant;
import com.github.cloud.common.core.constant.enums.ResultCode;
import com.github.cloud.common.core.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 客户端异常处理
 *
 * @author : czk
 * @date 2018-09-06
 */
@Component
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setCharacterEncoding(SysConstant.UTF8);
        response.setContentType(SysConstant.CONTENT_TYPE);
        response.setStatus(HttpStatus.OK.value());
        Result result = Result.failed(ResultCode.NOT_LOGIN_ERROR, "没有登录");
        PrintWriter printWriter = response.getWriter();
        printWriter.append(JSONObject.toJSONString(result));
    }

}
