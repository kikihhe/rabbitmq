package com.xiaohe.rocketmqstart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Order
 * @Description 订单对象
 * @Author 何
 * @Date 2023-07-09 22:20
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;

    private String name;

}
