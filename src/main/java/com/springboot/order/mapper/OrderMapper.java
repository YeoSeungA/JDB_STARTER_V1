package com.springboot.order.mapper;

import com.springboot.coffee.entity.Coffee;
import com.springboot.order.dto.*;
import com.springboot.order.entity.Order;
import com.springboot.order.entity.OrderCoffee;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    //    PatchDto를 Order Entity로
    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);
    //    List<Order> 을  List<OrderResponseDto>로
    List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders);
//    PostDto를 Order Entity로
    default Order orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Order order = new Order();
        order.setMember(orderPostDto.getMember());
        List<OrderCoffee> orderCoffees = orderPostDto.getOrderCoffees().stream()
                .map(orderCoffeeDto -> {
                    OrderCoffee orderCoffee = new OrderCoffee();
                    Coffee coffee = new Coffee();
                    coffee.setCoffeeId(orderCoffeeDto.getCoffeeId());
//                    orderCoffee.getCoffee().setKorName(coffee.getKorName());
//                    orderCoffee.getCoffee().setEngName(coffee.getEngName());
//                    orderCoffee.getCoffee().setPrice(coffee.getPrice());
//                    orderCoffee.getCoffee().setCoffeeCode(coffee.getCoffeeCode());
                    orderCoffee.setCoffee(coffee);
                    orderCoffee.setQuantity(orderCoffeeDto.getQuantity());
                    orderCoffee.setOrder(order);
                    return orderCoffee;
                }).collect(Collectors.toList());
        order.setOrderCoffees(orderCoffees);
        return order;
    }
    //    Order Entity를 OrderResponseDto로
    default OrderResponseDto orderToOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setMemberId(order.getMember().getMemberId());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        List<OrderCoffeeResponseDto> orderResponseDtos = order.getOrderCoffees().stream()
                .map(orderCoffee -> orderCoffeeToOrderCoffeeResponseDto(orderCoffee))
                .collect(Collectors.toList());
        orderResponseDto.setOrderCoffeeResponseDtos(orderResponseDtos);
        return orderResponseDto;
    }

    default OrderCoffeeResponseDto orderCoffeeToOrderCoffeeResponseDto(OrderCoffee orderCoffee){
        OrderCoffeeResponseDto orderCoffeeResponseDto = new OrderCoffeeResponseDto(
                orderCoffee.getCoffee().getCoffeeId(),
                orderCoffee.getCoffee().getPrice(),
                orderCoffee.getCoffee().getKorName(),
                orderCoffee.getCoffee().getEngName(),
                orderCoffee.getQuantity()
        );
        return orderCoffeeResponseDto;
    }
}
