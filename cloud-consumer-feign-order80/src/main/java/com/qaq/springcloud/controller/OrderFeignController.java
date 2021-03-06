package com.qaq.springcloud.controller;


import com.qaq.springcloud.entity.CommonResult;
import com.qaq.springcloud.entity.Payment;
import com.qaq.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        CommonResult<Payment> paymentById = paymentFeignService.getPaymentById(id);
        return paymentById;
    }
    @GetMapping("/consumer/payment/timeout")
    public String timeoutTest(){
        return paymentFeignService.getTimeoutTest();
    }
}
