package com.qaq.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private RestTemplate restTemplate;

    private static final String URL="http://cloud-payment-consul";
    @RequestMapping("/consumer/payment/consul")
    public String getString(){
        String forObject = restTemplate.getForObject(URL + "/payment/consul", String.class);
        return forObject;
    }
}
