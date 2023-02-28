package com.example.orderservice.controller;

import com.example.orderservice.service.external.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Random;

@RestController
@RequestMapping("/order")
@Slf4j
@AllArgsConstructor
public class OrderController {

    private final PaymentService paymentService;
    @GetMapping("/place")
    public Object placeOrder(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        /*int serverPort = request.getServerPort();
        response.setHeader("X-server-pot", String.valueOf(serverPort));
        */
        this.paymentService.checkBalance("mafei");
        return Collections.singletonMap(
                "msg",
                "success"
        );
    }
}
