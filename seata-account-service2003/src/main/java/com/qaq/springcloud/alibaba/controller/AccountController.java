package com.qaq.springcloud.alibaba.controller;

import com.qaq.springcloud.alibaba.domain.CommonResult;
import com.qaq.springcloud.alibaba.service.AccountService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    public CommonResult decrease(@RequestParam("userId")Long userId,@RequestParam("money") BigDecimal money){
        accountService.decrease(userId,money);
        return new CommonResult(200, "扣除余额成功");
    }
}
