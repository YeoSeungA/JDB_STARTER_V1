package com.springboot.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCoffeeResponseDto {
    private long coffeeId;
//    Integer??
    private int quantity;
    private String korName;
    private String engName;
    private int price;
}
