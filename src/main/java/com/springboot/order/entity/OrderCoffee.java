package com.springboot.order.entity;

import com.springboot.coffee.entity.Coffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class OrderCoffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderCoffeeId;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Coffee coffee;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;

    public void setOrder (Order order) {
        this.order = order;
        if(!order.getOrderCoffees().contains(this)) {
            order.addOrderCoffee(this);
        }
    }

//    public void addCoffee(Coffee coffee) {
//        this.coffee = coffee;
//        if(this.coffee.getOrderCoffees().contains(this)) {
//            this.coffee.addOrderCoffee(this);
//        }
//    }
}
