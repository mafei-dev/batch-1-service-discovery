package com.example.orderservice.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentServiceOF {
    @GetMapping("/balance")
    ResponseEntity<Map<?, ?>> checkBalance();
}
