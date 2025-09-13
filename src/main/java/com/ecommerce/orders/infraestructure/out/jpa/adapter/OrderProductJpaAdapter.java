package com.ecommerce.orders.infraestructure.out.jpa.adapter;

import com.ecommerce.orders.domain.model.OrderProduct;
import com.ecommerce.orders.domain.spi.IOrderProductPersistencePort;
import com.ecommerce.orders.infraestructure.out.jpa.mapper.IOrderProductEntityMapper;
import com.ecommerce.orders.infraestructure.out.jpa.repository.IOrderProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderProductJpaAdapter implements IOrderProductPersistencePort {

    private final IOrderProductRepository orderProductRepository;
    private final IOrderProductEntityMapper orderProductEntityMapper;

    @Override
    public void saveOrderItem(OrderProduct item) {
        System.out.println(item.toString());
        orderProductRepository.save(orderProductEntityMapper.toEntity(item));
    }
}
