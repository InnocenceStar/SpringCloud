package com.qaq.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.qaq.springcloud.alibaba.handler.CustomerBlockHandler;
import com.qaq.springcloud.entity.CommonResult;
import com.qaq.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handlerException")
    public CommonResult<Payment> byResource(){
        return new CommonResult<Payment>(200, "common result 200 ", new Payment(2020l, "serial001"));
    }
    public CommonResult<Payment> handlerException(BlockException e){
        return new CommonResult<>(444, e.getClass().getCanonicalName()+"not available");
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult<Payment> byUrl(){
        return new CommonResult<Payment>(200, "common result 200 ", new Payment(2020l, "serial002"));
    }


    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handlerException2")
    public CommonResult<Payment> customerBlockHandler(){
        return new CommonResult<Payment>(200, "common result 200 ", new Payment(2020l, "serial003"));
    }
}
