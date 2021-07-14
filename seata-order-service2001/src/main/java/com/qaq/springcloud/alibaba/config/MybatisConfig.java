package com.qaq.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.qaq.springcloud.alibaba.dao"})
public class MybatisConfig {
}
