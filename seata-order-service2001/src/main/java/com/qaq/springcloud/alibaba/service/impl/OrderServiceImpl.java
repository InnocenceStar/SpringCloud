package com.qaq.springcloud.alibaba.service.impl;

import com.qaq.springcloud.alibaba.dao.OrderDao;
import com.qaq.springcloud.alibaba.domain.Order;
import com.qaq.springcloud.alibaba.service.AccountService;
import com.qaq.springcloud.alibaba.service.OrderService;
import com.qaq.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;


    /**
     * 下订单 减库存 减余额 改状态
     * @param order
     */
    @Override
    @GlobalTransactional(name = "qaq-create-order",rollbackFor = Exception.class) //事务
    public void create(Order order) {
        log.info("------>订单开始,新建订单");
        orderDao.create(order);
        log.info("------>订单微服务开始调用库存，做扣减");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("------>库存扣减结束");
        log.info("------>调用账户，开始扣钱");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("------>金钱扣减结束");
        log.info("------>开始修改订单状态");
        orderDao.update(order.getUserId(), 0);
        log.info("------>结束修改订单状态");

        log.info("=====end=====");

    }
}
