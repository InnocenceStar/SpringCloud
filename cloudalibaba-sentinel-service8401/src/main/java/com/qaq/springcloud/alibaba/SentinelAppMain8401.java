package com.qaq.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SentinelAppMain8401 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelAppMain8401.class, args);
    }
}
