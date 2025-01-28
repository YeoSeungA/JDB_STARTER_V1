package com.springboot.order.dto;

import com.springboot.coffee.entity.Coffee;
import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class OrderCoffeeDto {
    @Positive
    private long coffeeId;

    @Positive
    private int quantity;

//    Coffee는 왜 coffeeId로 받을까? 객체로 받기엔 너무 데이터가 커서?
//    ok 근데 왜 getCoffee로 안 받을까??
//    private Coffee coffee;
//    orderId는 왜 필요가 없을까?
//    private long orderId;
}
