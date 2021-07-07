package com.qaq.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
//这是一个配置中心

//http://config-3344.com:3344/master/application-dev.yml
//对所有客户端的刷新
//刷新内容,广播通知>curl -X POST "http://localhost:3344/actuator/bus-refresh".
//刷新内容,定点通知>curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355".(config-client:3355 微服务名称：端口号)
@RefreshScope
@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
