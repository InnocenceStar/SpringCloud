package com.qaq.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderZkController {

    private static final String URL="http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/payment/zk")
    public String orderZk(){
        String forObject = restTemplate.getForObject(URL + "/payment/zk", String.class);
        return forObject;
    }
}
