package com.example.apigateway.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class AppGlobalFilter {

    @Bean
    public GlobalFilter customGlobalFilter1() {
        System.out.println("AppGlobalFilter.customGlobalFilter1:0");
        return (exchange, chain) -> {
            System.out.println("AppGlobalFilter.customGlobalFilter1:PRE");
            String id = exchange.getRequest().getId();
//            System.out.println("customGlobalFilter1:request-id:" + id);
            return chain
                    .filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        System.out.println("AppGlobalFilter.customGlobalFilter1:POST");
                    }));
        };
    }




    @Bean
    public GlobalFilter customGlobalFilter2() {
        System.out.println("AppGlobalFilter.customGlobalFilter2:0");
        return (exchange, chain) -> {
            System.out.println("AppGlobalFilter.customGlobalFilter2:PRE");
            String id = exchange.getRequest().getId();
//            System.out.println("customGlobalFilter2:request-id:" + id);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("AppGlobalFilter.customGlobalFilter2:POST");
            }));
        };
    }

    @Bean
    public GlobalFilter customGlobalFilter3() {
        System.out.println("AppGlobalFilter.customGlobalFilter3:0");
        return (exchange, chain) -> {
            System.out.println("AppGlobalFilter.customGlobalFilter3:PRE");
            String id = exchange.getRequest().getId();
//            System.out.println("customGlobalFilter3:request-id:" + id);
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        System.out.println("AppGlobalFilter.customGlobalFilter3:POST");
                    }));
        };
    }


}
