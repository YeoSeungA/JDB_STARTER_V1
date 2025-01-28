package com.springboot.order.mapper;

import com.springboot.coffee.entity.Coffee;
import com.springboot.order.dto.OrderCoffeeResponseDto;
import com.springboot.order.dto.OrderPatchDto;
import com.springboot.order.dto.OrderPostDto;
import com.springboot.order.dto.OrderResponseDto;
import com.springboot.order.entity.Order;
import com.springboot.order.entity.OrderCoffee;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
//    PostDto를 Order Entity로
    default Order orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Order order = new Order();
        order.setMember(orderPostDto.getMember());
        List<OrderCoffee> orderCoffees = orderPostDto.getOrderCoffee().stream()
                .map(orderCoffeeDto -> {
                    OrderCoffee orderCoffee = new OrderCoffee();
                    Coffee coffee = new Coffee();
                    coffee.setCoffeeId(orderCoffeeDto.getCoffeeId());
                    orderCoffee.getCoffee().setKorName(coffee.getKorName());
                    orderCoffee.getCoffee().setEngName(coffee.getEngName());
                    orderCoffee.getCoffee().setPrice(coffee.getPrice());
                    orderCoffee.getCoffee().setCoffeeCode(coffee.getCoffeeCode());
                    orderCoffee.setQuantity(orderCoffeeDto.getQuantity());
                    orderCoffee.setOrder(order);
                    return orderCoffee;
                }).collect(Collectors.toList());
        order.setOrderCoffees(orderCoffees);
        return order;
    }
//    PatchDto를 Order Entity로
    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);
//    Order Entity를 OrderResponseDto로
    default OrderResponseDto orderToOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setMemberId(order.getMember().getMemberId());
        orderResponseDto.setOrderCoffeeResponseDtos(orderCoffeeToOrderCoffeeResponseDtos(order.getOrderCoffees()));
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        return orderResponseDto;
    }
//    List<Order> 을  List<OrderResponseDto>로
    List<OrderResponseDto> orderToOrderResponseDtos(List<Order> order);

//    List<OrderCoffee>를 List<OrderCoffeeResponseDto로>
    default List<OrderCoffeeResponseDto> orderCoffeeToOrderCoffeeResponseDtos(List<OrderCoffee>orderCoffees) {
//        List<OrderCoffeeResponseDto> orderCoffeeResponseDtos = new ArrayList<>();
        OrderCoffeeResponseDto orderCoffeeResponseDto = new OrderCoffeeResponseDto();
        List<OrderCoffeeResponseDto> orderCoffeeResponseDtos = orderCoffees.stream()
                .map(orderCoffee -> {
                    orderCoffeeResponseDto.setCoffeeId(orderCoffee.getCoffee().getCoffeeId());
                    orderCoffeeResponseDto.setQuantity(orderCoffee.getQuantity());
                    orderCoffeeResponseDto.setKorName(orderCoffee.getCoffee().getKorName());
                    orderCoffeeResponseDto.setEngName(orderCoffee.getCoffee().getEngName());
                    orderCoffeeResponseDto.setPrice(orderCoffee.getCoffee().getPrice());
                    return orderCoffeeResponseDto;
                }).collect(Collectors.toList());
        return orderCoffeeResponseDtos;
    }
}
