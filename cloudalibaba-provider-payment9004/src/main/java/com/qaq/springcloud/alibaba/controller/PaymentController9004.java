package com.qaq.springcloud.alibaba.controller;

import com.qaq.springcloud.entity.CommonResult;
import com.qaq.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController9004 {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1l, new Payment(1l, "serial1"));
        hashMap.put(2l, new Payment(2l, "serial2"));
        hashMap.put(3l, new Payment(3l, "serial3"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentCommonResult(@PathVariable("id")Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> paymentCommonResult = new CommonResult<>(200, "from mysql ,port: " + serverPort,payment);
        return paymentCommonResult;
    }
}
