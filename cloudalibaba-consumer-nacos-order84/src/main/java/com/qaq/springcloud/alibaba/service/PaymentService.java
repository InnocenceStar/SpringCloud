package com.qaq.springcloud.alibaba.service;

import com.qaq.springcloud.alibaba.service.impl.PaymentServiceImpl;
import com.qaq.springcloud.entity.CommonResult;
import com.qaq.springcloud.entity.Payment;
import com.sun.xml.internal.ws.message.source.PayloadSourceMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentServiceImpl.class)
public interface PaymentService {

    @GetMapping("/paymentSQL/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
