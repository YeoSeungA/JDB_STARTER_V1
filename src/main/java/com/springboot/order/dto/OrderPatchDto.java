package com.springboot.order.dto;

import com.springboot.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
public class OrderPatchDto {
    @Setter
    private long orderId;
    private Order.OrderStatus orderStatus;

//    public void setOrderId(long orderId) {
//        this.orderId = orderId;
//    }
}
