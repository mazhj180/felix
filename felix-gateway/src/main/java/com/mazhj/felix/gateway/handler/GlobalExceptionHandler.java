package com.mazhj.felix.gateway.handler;

import com.alibaba.fastjson2.JSON;
import com.mazhj.common.core.exception.BusinessException;
import com.mazhj.common.core.exception.SystemException;
import com.mazhj.common.web.response.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 网关统一异常处理器
 * @author mazhj
 */
@Slf4j
@Order(-2)
@Component
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {
    @Override
    @NonNull
    public Mono<Void> handle(@NonNull ServerWebExchange exchange,@NonNull Throwable ex) {
        HttpStatus httpStatus = null;
        AjaxResult ajaxResult = null;
        if (ex instanceof SystemException){
            ajaxResult = AjaxResult.Builder.error();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof BusinessException) {
            ajaxResult = AjaxResult.Builder.unAuthorized();
            httpStatus = HttpStatus.UNAUTHORIZED;
        } else if (ex instanceof Exception) {
            ajaxResult = AjaxResult.Builder.error();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("网关服务内部异常 {}",ex.getMessage(),ex);
        }
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().setContentType(MediaType.APPLICATION_PROBLEM_JSON);

        byte[] bytes = JSON.toJSONString(ajaxResult).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);

        return response.writeWith(Mono.just(buffer));
    }
}
