package com.qaq.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/test/a")
    public String testA(){
        return "test a from sentinel app";
    }


    @GetMapping("/test/b")
    public String testB(){
        return "test b from sentinel app";
    }



    @GetMapping("/test/d")
    public String testD(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("===test d rt");
        return "test d from sentinel app";
    }
    @GetMapping("/test/e")
    public String testE(){
        int a=10/0;
        return "test e from sentinel app";
    }

    @GetMapping("/test/hotkey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2){

        return "hotkey";
    }
    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "deal hot key";
    }

}
