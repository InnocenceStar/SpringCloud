package com.qaq.springcloud.controller;

import com.qaq.springcloud.entity.CommonResult;
import com.qaq.springcloud.entity.Payment;
import com.qaq.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {


//    private static final String PAYMENT_URL="http://localhost:8001";
    private static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";//添加@LoadBalanced后，方能解析

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create", payment, CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getPaymentEntity(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        CommonResult body = forEntity.getBody();
        log.info("headers: "+forEntity.getHeaders().toString());
        return body;
    }

    /**
     * 调用自己的负载均衡算法，需要先注释@LoadBalance注解
     * @return
     */
    @GetMapping("/consumer/payment/lb")
    public String getLB(){

        List<ServiceInstance> services = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(services==null || services.size()<=0){
            return  null;
        }
        ServiceInstance instance = loadBalancer.instances(services);
        URI uri = instance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb", String.class);
    }


    @GetMapping("/consumer/payment/zipkin")
    public String zipkin(){

        return restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin", String.class);
    }
}
