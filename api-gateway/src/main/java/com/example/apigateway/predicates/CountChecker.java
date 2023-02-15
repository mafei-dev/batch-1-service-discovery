package com.example.apigateway.predicates;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

@Slf4j
@Component
public class CountChecker extends
        AbstractRoutePredicateFactory<CountChecker.Config> {


    public CountChecker() {
        super(CountChecker.Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        log.debug("CountChecker.apply");
        return exchange -> {
            log.debug("CountChecker.apply.sub<exchange>");
            log.debug("maxCount : {}",config.getMaxCount());
            HttpHeaders headers = exchange.getRequest().getHeaders();
            return headers.containsKey("X-count")
                    &&
                    Integer.parseInt(headers.get("X-count")
                            .get(0)) < config.getMaxCount();
        };
    }

    @Data
    @NoArgsConstructor
    public static class Config {
        private Integer maxCount;
    }

}
