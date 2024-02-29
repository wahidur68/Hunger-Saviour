package com.hunger.saviour.portal.services;

import com.hunger.saviour.portal.dtos.OrderDTO;
import com.hunger.saviour.portal.entities.OrderEntity;

import java.util.List;

public interface OrderService {
    void processOrder(OrderEntity orderEntity);
    List<OrderDTO> getOrders();
}
