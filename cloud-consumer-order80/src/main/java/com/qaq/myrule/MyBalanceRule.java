package com.qaq.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBalanceRule {

    @Bean
    public IRule myRule(){
        return new RandomRule();//设置为随机的负载均衡
    }
}
