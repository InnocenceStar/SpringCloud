package com.qaq.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.qaq.springcloud.entity.CommonResult;

//自定义服务降级方法
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException e){
        return new CommonResult<>(4444, e.getClass().getCanonicalName()+"not available by global exception 1");
    }
    public static CommonResult handlerException2(BlockException e){
        return new CommonResult<>(4444, e.getClass().getCanonicalName()+"not available by global exception 2");
    }
}
