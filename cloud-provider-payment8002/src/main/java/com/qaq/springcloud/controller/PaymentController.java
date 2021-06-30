package com.qaq.springcloud.controller;

import com.qaq.springcloud.entity.CommonResult;
import com.qaq.springcloud.entity.Payment;
import com.qaq.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("插入结果"+i);
        if(i>0){
            return new CommonResult(200, "插入成功,端口号为："+serverPort, i);
        }else {
            return new CommonResult(444, "插入失败", null);
        }
    }
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果"+payment);
        if(payment != null){
            return new CommonResult(200, "查找成功,端口号为："+serverPort, payment);
        }else {
            return new CommonResult(444, "没有找到"+id, null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getServerPort(){

        return this.serverPort;
    }
}
