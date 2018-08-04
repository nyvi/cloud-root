package com.github.cloud.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.github.cloud.common.constant.SysConstant;
import com.github.cloud.common.constant.enums.ResultCode;
import com.github.cloud.common.util.Result;
import com.github.cloud.common.util.StrUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;

/**
 * @author : czk
 * @date 2018-07-28
 */
@Component
public class AccessFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token = this.getToken(request);
        if (StrUtils.isBlank(token)) {
            return renderErrorResponse(exchange, Result.failed(ResultCode.NO_AUTHORITY, "没有权限"));
        }
        // TODO:check permission
        return chain.filter(exchange);
    }

    private String getToken(@Nonnull ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(SysConstant.HEAD_AUTHORIZATION);
        if (StrUtils.isBlank(token)) {
            return StrUtils.EMPTY;
        }
        return StrUtils.removeStart(token, SysConstant.TOKEN_SPLIT);
    }

    @NotNull
    private Mono<Void> renderErrorResponse(ServerWebExchange exchange, Result result) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        byte[] bytes = JSONObject.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }

}
