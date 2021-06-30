package com.qaq.springcloud.service;

import com.qaq.springcloud.entity.Payment;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
