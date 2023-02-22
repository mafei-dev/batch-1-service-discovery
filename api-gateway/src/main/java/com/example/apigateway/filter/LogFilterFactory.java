package com.example.apigateway.filter;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class LogFilterFactory extends AbstractGatewayFilterFactory<LogFilterFactory.Config> {

    public LogFilterFactory() {
        super(LogFilterFactory.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        System.out.println("LogFilterFactory.apply");
        System.out.println("config:1 = " + config);
        return (exchange, chain) -> {
            LocalDateTime now = LocalDateTime.now();
            System.out.println("LogFilterFactory.apply:PreFilter");
            System.out.println("config:1 = " + config);
            //do something you want. [update the request {header}]
            ServerHttpRequest request = exchange.getRequest();
            String id = request.getId();
            //01:pre
            log.debug("request-id : {}", id);
            List<String> strings = exchange.getRequest().getHeaders().get("X-count");
            System.out.println("strings = " + strings);

            ServerHttpRequest.Builder mutateBuilder = exchange.getRequest().mutate();
            ServerHttpRequest request1 = mutateBuilder.header(
                            "X-Gateway-In",
                            String.valueOf(now)
                    )
                    .build();

            ServerWebExchange.Builder mutate = exchange.mutate();
            mutate.request(request1);
            ServerWebExchange exchange1 = mutate.build();
            return chain
                    .filter(exchange1)
                    .then(
                            Mono.fromRunnable(() -> {
                                        //post filter
                                        LocalDateTime out = LocalDateTime.now();
                                        System.out.println("LogFilterFactory.apply:PostFilter");
                                        ServerHttpResponse response = exchange.getResponse();
                                        response.getHeaders().add("X-Gateway-out", String.valueOf(out));
                                        response.getHeaders().add("X-Gateway-message", config.getMessage());
                                    }
                            )
                    );
        };
    }

    @Data
    @ToString
    public static class Config {
        private String message;
    }

}
