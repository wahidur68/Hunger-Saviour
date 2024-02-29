package com.hunger.saviour.portal.apis;

import com.hunger.saviour.portal.dtos.OrderDTO;
import com.hunger.saviour.portal.entities.OrderEntity;
import com.hunger.saviour.portal.repositories.OrderRepository;
import com.hunger.saviour.portal.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderAPI {
private final OrderService orderService;

    @GetMapping("/{username}")
    public List<OrderDTO> getOrdersByUsername(){
        // tmrw implementation
        return null;
    }
    @GetMapping
    public List<OrderDTO> getOrders(){
        return this.orderService.getOrders().stream().sorted(Comparator.comparing(OrderDTO::getCreatedDate).reversed()).collect(Collectors.toList());
    }
}
