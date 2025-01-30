package com.springboot.order.controller;

import com.springboot.order.dto.OrderPatchDto;
import com.springboot.order.dto.OrderPostDto;
import com.springboot.order.dto.OrderResponseDto;
import com.springboot.order.entity.Order;
import com.springboot.order.mapper.OrderMapper;
import com.springboot.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v11/orders")
@Validated
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    public OrderController(OrderMapper orderMapper, OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderPostDto orderPostDto) {
        Order order = orderService.createdOrder(orderMapper.orderPostDtoToOrder(orderPostDto));
        OrderResponseDto response = orderMapper.orderToOrderResponseDto(order);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{order-id}")
    public ResponseEntity patchOrder(@Positive @PathVariable("order-id") long orderId,
                                     @Valid OrderPatchDto orderPatchDto) {
        orderPatchDto.setOrderId(orderId);
        Order order = orderService.updateOrder(orderMapper.orderPatchDtoToOrder(orderPatchDto));
        OrderResponseDto response = orderMapper.orderToOrderResponseDto(order);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@Positive @PathVariable("order-id") long orderId) {
        Order order = orderService.findOrder(orderId);
        OrderResponseDto response = orderMapper.orderToOrderResponseDto(order);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders() {
        List<Order> orders = orderService.findOrders();
        List<OrderResponseDto> responses = orderMapper.ordersToOrderResponseDtos(orders);

        return new ResponseEntity(responses, HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity deleteOrder(@Positive @PathVariable("order-id") long orderId) {
        orderService.cancleOrder(orderId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
