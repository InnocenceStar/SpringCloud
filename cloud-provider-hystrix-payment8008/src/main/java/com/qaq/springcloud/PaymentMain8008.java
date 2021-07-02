package com.qaq.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker //启动hystrix服务降级
public class PaymentMain8008 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8008.class, args);
    }

    /**
     * 此配置为服务监控配置，和服务容错本身无关，springcloud升级后的坑
     *ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream"
     * 配置完成后即可
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(hystrixMetricsStreamServlet);
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.addUrlMappings("/hystrix.stream");
        servletRegistrationBean.setName("HystrixMetricsStreamServlet");
        return servletRegistrationBean;
    }
}
