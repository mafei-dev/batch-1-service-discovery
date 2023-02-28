package com.example.orderservice.controller;

import com.example.orderservice.service.external.PaymentService;
import com.example.orderservice.service.external.PaymentServiceOF;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/order")
@Slf4j
@AllArgsConstructor
public class OrderController {

    private final PaymentServiceOF paymentServiceOF;
    private final PaymentService paymentService;
    @GetMapping("/place")
    public Object placeOrder(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        /*int serverPort = request.getServerPort();
        response.setHeader("X-server-pot", String.valueOf(serverPort));
        */
        ResponseEntity<Map<?, ?>> mapResponseEntity = this.paymentServiceOF.checkBalance();
        List<String> strings = mapResponseEntity.getHeaders().get("X-server-pot");
        assert strings != null;
        strings.forEach(s -> {
            System.out.println("X-server-pot OF = " + s);
        });
        this.paymentService.checkBalance("mafei");
        return Collections.singletonMap(
                "msg",
                "success"
        );
    }
}
