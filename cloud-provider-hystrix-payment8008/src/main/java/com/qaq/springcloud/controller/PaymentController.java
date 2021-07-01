package com.qaq.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.qaq.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String s = paymentService.paymentInfo_Ok(id);
        log.info("result"+s);
        return s;
    }
    //演示服务降级
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        String s = paymentService.paymentInfo_Timeout(id);
        log.info("result"+s);
        return s;
    }

    //演示服务熔断
    @GetMapping("/payment/hystrix/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String s = paymentService.paymentCircuitBreaker(id);
        log.info("===result===:"+s);
        return s;
    }
}
