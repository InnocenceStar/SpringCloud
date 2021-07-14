package com.qaq.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long id;

    private Long userId;

    //小金库
    private BigDecimal total;

    private BigDecimal used;
    //剩余额度
    private BigDecimal residue;

}
