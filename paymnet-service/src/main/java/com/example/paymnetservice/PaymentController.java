package com.example.paymnetservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Random;

@RestController
public class PaymentController {
    @GetMapping("/balance")
    public Object getBalance(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        int serverPort = request.getServerPort();
        response.setHeader("X-server-pot", String.valueOf(serverPort));
        return Collections.singletonMap(
                "balance",
                new Random().nextLong()
        );
    }
}
