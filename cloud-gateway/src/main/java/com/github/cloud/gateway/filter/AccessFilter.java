package com.github.cloud.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.github.cloud.common.core.constant.SysConstant;
import com.github.cloud.common.core.util.Result;
import com.github.cloud.common.core.util.StrUtils;
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
 * @author : huweihua
 * @date 2023-07-28
 */
@Component
public class AccessFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token = this.getToken(request);
        /// if (StrUtils.isBlank(token)) {
            /// return renderErrorResponse(exchange, Result.failed(ResultCode.NOT_LOGIN_ERROR, "no login"));
        /// }
        // TODO:check token
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
