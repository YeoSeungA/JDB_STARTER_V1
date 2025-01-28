package com.springboot.order.service;

import com.springboot.coffee.service.CoffeeService;
import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExcepionCode;
import com.springboot.member.service.MemberService;
import com.springboot.order.entity.Order;
import com.springboot.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final CoffeeService coffeeService;

    public OrderService(OrderRepository orderRepository, MemberService memberService, CoffeeService coffeeService) {
        this.orderRepository = orderRepository;
        this.memberService = memberService;
        this.coffeeService = coffeeService;
    }

    public Order createdOrder(Order order) {
//        order를 만들기 위해 member와 coffee가 존재하는지 확인해야 한다.
        checkRightOrder(order);
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
//        존재하는 order인지 확인. 있어야 update 가능.]
        Order pacthOrder = findVerifiedOrder(order.getOrderId());
        Optional.ofNullable(order.getOrderStatus())
                .ifPresent(orderStatus -> order.setOrderStatus(orderStatus));
        return orderRepository.save(pacthOrder);
    }

    public Order findOrder(long orderId) {
       return findVerifiedOrder(orderId);
    }

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    public void cancleOrder(long orderId) {
//        존재하는 order인지 유효성 검증
        Order order = findVerifiedOrder(orderId);
//        해당 order의 status가 2 미만일 때만 주문 취소가 가능하다.(실제 삭제X, 상태가 변화하는 것.)
//        2 이상일때는 message로 알려주자.
        if(order.getOrderStatus().getNumber() >= 2) {
            throw new BusinessLogicException(ExcepionCode.CANNOT_CHANGE_ORDER);
        }
        order.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        orderRepository.save(order);
    }

    private void checkRightOrder(Order order) {
//        회원이 존재하는지 확인하자.
        memberService.existMember(order.getMember().getMemberId());
//        커피가 존재하는가
        order.getOrderCoffees().stream()
                .forEach(orderCoffee -> coffeeService.findVerifiedCoffee(orderCoffee.getCoffee().getCoffeeId()));
    }

    private Order findVerifiedOrder(long orderId) {
        Order order = orderRepository.findById(orderId)
                        .orElseThrow(() -> new BusinessLogicException(ExcepionCode.ORDER_NOT_FOUND));
        return order;
    }
}
