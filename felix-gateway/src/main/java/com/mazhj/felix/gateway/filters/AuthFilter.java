package com.mazhj.felix.gateway.filters;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mazhj.common.core.exception.AuthException;
import com.mazhj.common.core.exception.BusinessException;
import com.mazhj.common.core.exception.SystemException;
import com.mazhj.common.core.utils.SpringUtil;
import com.mazhj.common.pojo.claims.Claims;
import com.mazhj.felix.gateway.config.properties.GatewayConfigProperties;
import com.mazhj.felix.gateway.resp.AjaxResultForReactive;
import com.nimbusds.jose.JOSEException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

/**
 * @author mazhj
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    private static final String[] SYSTEM_NOT_CHECK_PATH = {"/index/[^\\s]*","/book/[^\\s]*"};

    private final WebClient webClient;

    public AuthFilter(WebClient webClient) {
        this.webClient = webClient;
    }

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
            request = request
                    .mutate()
                    .headers(httpHeaders -> {
                        httpHeaders.set("userId",claims.getUserId());
                        httpHeaders.set("Account-Level",claims.getLevel());
                    })
                    .build();

            if (isWebSocketUpgradeRequest(request)){
                MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>(request.getQueryParams());
                queryParams.remove("token");
                URI originalUri = request.getURI();
                URI newUri = URI.create(originalUri.getScheme() + "://" + originalUri.getAuthority() + originalUri.getPath());
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
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Claims validate(String token) throws AuthException, ParseException, JOSEException, ExecutionException, InterruptedException {
        if (Objects.equals(token, "") || token == null){
            throw new AuthException("unauthorized exception");
        }
        Mono<String> mono = this.webClient.get()
                .uri("/valid?accessToken="+token)
                .retrieve()
                .bodyToMono(String.class);
        CompletableFuture<String> voidCompletableFuture = CompletableFuture.supplyAsync(mono::block);
        AjaxResultForReactive result = JSON.parseObject(voidCompletableFuture.get(), AjaxResultForReactive.class);
        assert result != null;
        JSONObject data = result.getData();
        if (!data.getBoolean("access")){
            throw new AuthException(data.getString("message"));
        }
        return data.getObject("claims",Claims.class);
    }

    private boolean isWebSocketUpgradeRequest(ServerHttpRequest request) {
        String upgrade = request.getHeaders().getUpgrade();
        if (upgrade == null || upgrade.isEmpty()){
            return false;
        }
        return "websocket".equalsIgnoreCase(upgrade);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
