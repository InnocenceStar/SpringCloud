package com.qaq.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
}
