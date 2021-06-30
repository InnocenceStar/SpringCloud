package com.qaq.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FeignConfig {
    /**
     * 设置feign的日志监控级别
     * @return
     */
    @Bean
    public Logger.Level feignLevel(){
        return Logger.Level.FULL;
    }
}
