package com.springboot.order.dto;

import com.springboot.member.entity.Member;
import com.springboot.order.entity.OrderCoffee;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
public class OrderPostDto {
    @Positive
    private long memberId;

//    @Valid
    @NotNull
    private List<OrderCoffeeDto> orderCoffee;

//   memberId만 알아도 Member 객체를 생성할 수 있다.
    public Member getMember(/*long memberId가 없어도 되나용?>*/) {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }
}
