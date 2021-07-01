package com.qaq.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.qaq.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalFallback")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String s = paymentHystrixService.paymentInfo_OK(id);
        log.info("result"+s);
        return s;
    }
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    //指定服务降级的方法
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    //8008宕机，调用默认的全局fallback方法
    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        String s = paymentHystrixService.paymentInfo_Timeout(id);
        log.info("result"+s);
        return s;
    }


    public String paymentInfo_TimeoutHandler( Integer id){
        return "猛男哭泣，嘤嘤嘤";
    }

    /**
     * 全局服务降级FallBack方法
     * @return
     */
    public String globalFallback(){
        return "globalFallback method execute";
    }
}
