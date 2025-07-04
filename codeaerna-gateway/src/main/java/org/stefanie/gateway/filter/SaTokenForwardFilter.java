package org.stefanie.gateway.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class SaTokenForwardFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从请求头获取 satoken
        String satoken = exchange.getRequest().getHeaders().getFirst("satoken");
        if (satoken != null) {
            // 把 satoken 加到下游请求头
            ServerWebExchange newExchange = exchange.mutate()
                .request(builder -> builder.header("satoken", satoken))
                .build();
            return chain.filter(newExchange);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100; // 优先级高于大多数自定义过滤器
    }
}