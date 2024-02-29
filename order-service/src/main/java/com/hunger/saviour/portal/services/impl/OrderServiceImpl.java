package com.hunger.saviour.portal.services.impl;

import com.hunger.saviour.portal.dtos.OrderDTO;
import com.hunger.saviour.portal.entities.OrderEntity;
import com.hunger.saviour.portal.repositories.OrderRepository;
import com.hunger.saviour.portal.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void processOrder(OrderEntity orderEntity) {
        this.orderRepository.save(orderEntity);
    }
    @Override
  public List<OrderDTO> getOrders(){
        List<OrderEntity> orderEntityList = this.orderRepository.findAll();
        List<OrderDTO> orderDTOList=new ArrayList<>();
        for(OrderEntity entity:orderEntityList){
            OrderDTO orderDto = OrderDTO.builder().
                    orderJson(entity.getOrder_json())
                    .createdDate(entity.getCreatedDate())
                    .updatedDate(entity.getUpdatedDate())
                    .orderStatus(entity.getOrderStatus())
                    .totalPrice(entity.getTotalPrice())
                    .transactionId(entity.getTransactionId())
                    .orderId(entity.getId())
                    .build();
            orderDTOList.add(orderDto);
        }

        return orderDTOList;
    }
}
