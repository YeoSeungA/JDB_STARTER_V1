package com.springboot.order.dto;

import com.springboot.member.entity.Member;
import com.springboot.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderResponseDto {
    private long orderId;
    private long memberId;
    private List<OrderCoffeeResponseDto> orderCoffeeResponseDtos;
    private Order.OrderStatus orderStatus;

//    public void getMember(Member member) {
//        this.memberId = member.getMemberId();
//    }
}
