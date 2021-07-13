package com.qaq.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.qaq.springcloud.entity.CommonResult;
import com.qaq.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.IllegalFormatException;

@RestController
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @Resource
    private RestTemplate restTemplate;


    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback",fallback = "handlerFallback") //fallback只负责业务异常
//    @SentinelResource(value = "fallback",blockHandler = "blockHandler") //只负责sentinel控制台配置违规
    @SentinelResource(value = "fallback",blockHandler = "blockHandler",fallback = "handlerFallback"
                        ,exceptionsToIgnore = {IllegalArgumentException.class}) //只负责sentinel控制台配置违规,exceptionsToIgnore让fallback可以忽视其类型的异常
    public CommonResult<Payment> fallback(@PathVariable("id")Long id){
        CommonResult result = restTemplate.getForObject(serverURL + "/paymentSQL/" + id, CommonResult.class, id);
        if(id==4){
            throw new IllegalArgumentException("IllegalArgumentException ,非法参数");
        }else if (result.getData()==null){
            throw new NullPointerException("NullPointerException，空指针异常");
        }

        return result;
    }

    /**
     * fallback 处理
     * @param id
     * @param e
     * @return
     */
    public CommonResult<Payment> handlerFallback(@PathVariable Long id,Throwable e){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(444, "兜底异常，qaq，e："+e.getMessage(),payment );
    }

    /**
     * blockhandler 处理
     * @param id
     * @param e
     * @return
     */
    public CommonResult<Payment> blockHandler(@PathVariable Long id, BlockException e){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(445, "block exception , sentinel ,qaq，e："+e.getMessage(),payment );
    }

}
