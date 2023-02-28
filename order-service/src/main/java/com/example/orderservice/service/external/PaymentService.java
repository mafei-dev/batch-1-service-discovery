package com.example.orderservice.service.external;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PaymentService {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    public PaymentService(DiscoveryClient discoveryClient, @Qualifier("restTemplate1") RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }


    public void checkBalance(String username) {
        log.debug("username : {}", username);
        List<ServiceInstance> instances = discoveryClient.getInstances("payment-service");
        instances.forEach(instanceInfo -> {
            System.out.println("instanceInfo = " + instanceInfo);
        });
        //call the external service:payment-service
        ResponseEntity<Map> entity = restTemplate.getForEntity("http://PAYMENT-SERVICE/balance", Map.class);
        List<String> strings = entity.getHeaders().get("X-server-pot");
        assert strings != null;
        strings.forEach(s -> {
            System.out.println("X-server-pot = " + s);
        });
        Map body = entity.getBody();
        assert body != null;
        Long balance = (Long) body.get("balance");
        log.debug("balance is : {}", balance);
    }
}
