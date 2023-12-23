package com.mazhj.felix.gateway.filters;

import com.mazhj.common.core.exception.AuthException;
import com.mazhj.common.core.exception.BusinessException;
import com.mazhj.common.core.exception.SystemException;
import com.mazhj.common.core.utils.JwtUtil;
import com.mazhj.common.core.utils.SpringUtil;
import com.mazhj.common.pojo.claims.Claims;
import com.mazhj.felix.gateway.config.properties.GatewayConfigProperties;
import com.nimbusds.jose.JOSEException;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author mazhj
 */
@Component
@EnableConfigurationProperties(value = {GatewayConfigProperties.class})
public class AuthFilter implements GlobalFilter, Ordered {

    private static final String[] SYSTEM_NOT_CHECK_PATH = {"/user/login","/index/[^\\s]*","/book/[^\\s]*"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpRequest request = exchange.getRequest();
            String requestPath = request.getPath().toString();
            List<String> whiteList = SpringUtil.getBean(GatewayConfigProperties.class).getWhiteList();
            boolean isAccess = whiteList.stream().anyMatch(path -> path.equals(requestPath));

            for (String path:SYSTEM_NOT_CHECK_PATH) {
                if (Pattern.matches(path,requestPath) || isAccess){
                    return chain.filter(exchange);
                }
            }
            String token = Optional.ofNullable(
                    request.getHeaders()
                            .getFirst("Authentication")
                    ).orElse(request.getQueryParams().getFirst("token"));
            Claims claims = validate(token);
            request = request.mutate().headers(httpHeaders -> {
                httpHeaders.set("userId",claims.getUserId());
            }).build();

            if (isWebSocketUpgradeRequest(request)){
                MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>(request.getQueryParams());
                queryParams.remove("token");
                URI originalUri = request.getURI();
                URI newUri = URI.create(originalUri.getScheme() + "://" + originalUri.getAuthority() + originalUri.getPath());
                System.out.println(newUri);
                request = request.mutate()
                        .uri(newUri)
                        .headers(httpHeaders -> httpHeaders.addAll(queryParams))
                        .build();
            }
            exchange.mutate().request(request).build();
            return chain.filter(exchange);
        } catch (ParseException | JOSEException e) {
            throw new SystemException(e.getMessage());
        } catch (AuthException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private Claims validate(String token) throws AuthException, ParseException, JOSEException {
        return JwtUtil.validateToken(token);
    }

    private boolean isWebSocketUpgradeRequest(ServerHttpRequest request) {
        String upgrade = request.getHeaders().getUpgrade();
        if (upgrade == null || upgrade.isEmpty()){
            return false;
        }
        return upgrade.equalsIgnoreCase("websocket");
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
