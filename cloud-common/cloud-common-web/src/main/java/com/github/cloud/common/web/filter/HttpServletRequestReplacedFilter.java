package com.github.cloud.common.web.filter;


import com.github.cloud.common.core.constant.SysConstant;
import com.github.cloud.common.core.util.StrUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nonnull;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决 request.getInputStream 只读取一次问题
 *
 * @author : czk
 * @date 2018-10-07
 */
public class HttpServletRequestReplacedFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {

        if (StrUtils.isNotBlank(request.getContentType()) && StrUtils.containsIgnoreCase(request.getContentType(), SysConstant.CONTENT_TYPE)) {
            filterChain.doFilter(new CustomHttpServletRequestWrapper(request), response);
        } else {
            filterChain.doFilter(request, response);
        }

    }

}
