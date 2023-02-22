package com.example.apigateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalFilter2 implements GlobalFilter,Ordered {
    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("GlobalFilter2.filter:PRE");
        //do something when the request goes down to the utility services
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            //do something when the request comes back
            System.out.println("GlobalFilter2.filter:POST");
        }));
    }
}
