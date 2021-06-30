package com.qaq.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    /**
     * 负载均衡算法：getAndIncrement()获取访问为第几次 %  instances.size()服务实例的数量
     * @param instances 服务器实例
     * @return 调用的实例
     */
    ServiceInstance instances(List<ServiceInstance> instances);
}
