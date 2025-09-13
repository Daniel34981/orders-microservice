package com.ecommerce.orders.infraestructure.out.jpa.adapter;

import com.ecommerce.orders.domain.model.Order;
import com.ecommerce.orders.domain.model.OrderProduct;
import com.ecommerce.orders.domain.spi.IOrderPersistencePort;
import com.ecommerce.orders.infraestructure.out.jpa.entity.OrderEntity;
import com.ecommerce.orders.infraestructure.out.jpa.mapper.IOrderEntityMapper;
import com.ecommerce.orders.infraestructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Override
    public Long createOrder(Order order) {
        OrderEntity entity = orderEntityMapper.toEntity(order);
        OrderEntity saved = orderRepository.save(entity);
        return saved.getId();
    }
}
