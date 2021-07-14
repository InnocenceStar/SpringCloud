package com.qaq.springcloud.alibaba.controller;

import com.qaq.springcloud.alibaba.domain.CommonResult;
import com.qaq.springcloud.alibaba.domain.Order;
import com.qaq.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
//  http://localhost:2001/order/create?userld=1&productld=1&count=10&money=100
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }
}
