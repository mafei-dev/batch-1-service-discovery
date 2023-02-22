package com.example.paymnetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@SpringBootApplication
//@EnableEurekaClient
//public class PaymentServiceApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(PaymentServiceApplication.class, args);
//    }
//
//}
public class PaymentServiceApplication {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a", "b", "c", "d");
        strings.forEach(s -> {
            System.out.println("s = " + s);
        });

        List<String> collect = strings.stream()
                .map(s -> {
                    System.out.println("char = " + s);
                    return LocalDateTime.now() + ":" + s;
                })
                .map(s -> {
                    System.out.println("After Modified CHAR = " + s);
                    return s;
                })
                .collect(Collectors.toList());

        Flux.fromArray(strings.toArray())
                .map(s -> {
                    System.out.println("char = " + s);
                    return LocalDateTime.now() + ":" + s;
                })
                .doOnNext(s -> {
                    System.out.println("s = " + s);
                })
                .collectList()
                .subscribe(strings1 -> {
                    System.out.println("strings: = " + strings1);
                });

    }

}

