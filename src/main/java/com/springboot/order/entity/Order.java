package com.springboot.order.entity;

import com.springboot.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "ORDER")
    private List<OrderCoffee> orderCoffees = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;


    public void addOrderCoffee (OrderCoffee orderCoffee) {
        orderCoffees.add(orderCoffee);

        if(orderCoffee.getOrder() != this) {
            orderCoffee.setOrder(this);
        }
    }

    public void setMember(Member member) {
        this.member = member;
        if(!member.getOrders().contains(this)) {
            member.addOrder(this);
        }
    }

    public enum OrderStatus {
        ORDER_REQUEST(1, "주문 중"),
        ORDER_CONFIRM(2, "주문 확정"),
        ORDER_COMPLETE(3, "주문 처리 완료"),
        ORDER_CANCEL(4, "주문 취소");

        @Getter
        private int number;

        @Getter
        private String description;

        OrderStatus(int number, String description) {
            this.number = number;
            this.description = description;
        }
    }
}
