package com.qaq.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {

    private Long id;

    private Long productId;
    //库存
    private Integer total;

    private Integer used;
    //剩余库存
    private Integer residue;

}
