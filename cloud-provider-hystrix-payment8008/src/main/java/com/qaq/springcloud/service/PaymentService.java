package com.qaq.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {


    public String paymentInfo_Ok(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_Ok,id: "+id+"\t"+"๑乛◡乛๑";
    }

    //当前方法若运行超过3秒，则调用paymentInfo_TimeoutHandler()方法

    /**
     * 服务降级演示
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_Timeout(Integer id){
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //HystrixCommand也可以处理计算异常
//        int b=10/0;
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_Timeout,id: "+id+"\t"+"(╥╯^╰╥)";
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+" 8001:paymentInfo_TimeoutHandler,id: "+id+"\t"+"▄︻┻┳═一……";
    }


    /**
     * 服务熔断演示
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间范围，时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("id < 0");
        }
        String s = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+s;
    }
    public String paymentCircuitBreakerFallback(Integer id){

        return "id < 0,error";
    }

}
