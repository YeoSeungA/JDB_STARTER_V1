package com.springboot.coffee.entity;

import com.springboot.order.entity.OrderCoffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coffeeId;

    @Column(nullable = false)
    private String korName;

    @Column(nullable = false)
    private String engName;

    @Column(nullable = false)
//    왜 Intetger일까?
    private Integer price;

    @Column(nullable = false, unique = true, length = 3)
    private String coffeeCode;

    @OneToMany(mappedBy = "coffee")
    private List<OrderCoffee> orderCoffees = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CoffeeStatus coffeeStatus = CoffeeStatus.COFFEE_FOR_SALE;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime modifiedAt = LocalDateTime.now();

    public enum CoffeeStatus {
        COFFEE_FOR_SALE ("판매중"),
        COFFEE_SOLD_OUT ("판매중지");

        @Getter
        private String status;

        CoffeeStatus(String status) {
            this.status = status;
        }
    }

//    public void addOrderCoffee(OrderCoffee orderCoffee) {
//        orderCoffees.add(orderCoffee);
//        if(orderCoffee.getCoffee() != this) {
//            orderCoffee.addCoffee(this);
//        }
//    }
}
