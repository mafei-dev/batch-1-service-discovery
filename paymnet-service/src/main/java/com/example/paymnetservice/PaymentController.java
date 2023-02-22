package com.example.paymnetservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Random;

@RestController
@Slf4j
public class PaymentController {
    @GetMapping("/balance")
    public Object getBalance(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
//        request.getParameterMap().put("xyz", new String[]{});
        String gatewayIn = request.getHeader("X-Gateway-In");
        String count = request.getHeader("X-count");
        log.debug("X-Gateway-In : {} , X-count : {}", gatewayIn, count);


        int serverPort = request.getServerPort();
        response.setHeader("X-server-pot", String.valueOf(serverPort));
        return Collections.singletonMap(
                "balance",
                new Random().nextLong()
        );
    }
}
