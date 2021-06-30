package com.qaq.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker //启动hystrix服务降级
public class PaymentMain8008 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8008.class, args);
    }
}
