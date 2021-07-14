package com.qaq.springcloud.alibaba.service.impl;

import com.qaq.springcloud.alibaba.service.PaymentService;
import com.qaq.springcloud.entity.CommonResult;
import com.qaq.springcloud.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        Payment payment = new Payment(id, null);
        return new CommonResult<>(1234, "block exception , sentinel ,qaq，e：",payment );
    }
}
