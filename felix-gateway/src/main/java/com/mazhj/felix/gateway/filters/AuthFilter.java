package com.mazhj.felix.gateway.filters;

import com.mazhj.common.core.exception.AuthException;
import com.mazhj.common.core.exception.BusinessException;
import com.mazhj.common.core.exception.SystemException;
import com.mazhj.common.core.utils.JwtUtil;
import com.mazhj.common.core.utils.SpringUtil;
import com.mazhj.common.pojo.claims.Claims;
import com.mazhj.felix.gateway.config.properties.GatewayConfigProperties;
import com.nimbusds.jose.JOSEException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author mazhj
 */
@Order(-1)
@Component
@EnableConfigurationProperties(value = {GatewayConfigProperties.class})
public class AuthFilter implements GlobalFilter {

    private static final String[] SYSTEM_NOT_CHECK_PATH = {"/index/\\S*","/book/\\S*"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        try {
            String requestPath = exchange.getRequest().getPath().toString();
            List<String> whiteList = SpringUtil.getBean(GatewayConfigProperties.class).getWhiteList();
            boolean isAccess = whiteList.stream().anyMatch(path -> path.equals(requestPath));

            for (String path:SYSTEM_NOT_CHECK_PATH) {
                if (Pattern.matches(path,requestPath) || isAccess){
                    return chain.filter(exchange);
                }
            }
            String token = exchange.getRequest().getHeaders().getFirst("Authentication");
            Claims claims = JwtUtil.validateToken(token);
            ServerHttpRequest request = exchange.getRequest()
                    .mutate().headers( httpHeaders -> {
                        httpHeaders.set("userId",claims.getLoginName());
                    }).build();

            exchange.mutate().request(request).build();

            return chain.filter(exchange);
        } catch (ParseException | JOSEException e) {
            throw new SystemException(e.getMessage());
        } catch (AuthException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
