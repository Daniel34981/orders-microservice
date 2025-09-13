package com.ecommerce.orders.infraestructure.configuration;

import com.ecommerce.orders.domain.api.IOrderServicePort;
import com.ecommerce.orders.domain.spi.IOrderPersistencePort;
import com.ecommerce.orders.domain.spi.IOrderProductPersistencePort;
import com.ecommerce.orders.domain.usecase.OrderUseCase;
import com.ecommerce.orders.infraestructure.out.jpa.adapter.OrderJpaAdapter;
import com.ecommerce.orders.infraestructure.out.jpa.adapter.OrderProductJpaAdapter;
import com.ecommerce.orders.infraestructure.out.jpa.mapper.IOrderEntityMapper;
import com.ecommerce.orders.infraestructure.out.jpa.mapper.IOrderProductEntityMapper;
import com.ecommerce.orders.infraestructure.out.jpa.repository.IOrderProductRepository;
import com.ecommerce.orders.infraestructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderProductRepository orderProductRepository;
    private final IOrderProductEntityMapper orderProductEntityMapper;

    @Bean
    public IOrderPersistencePort orderPersistence() {
        return new OrderJpaAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public IOrderProductPersistencePort orderProductPersistence()  {
        return new OrderProductJpaAdapter(orderProductRepository, orderProductEntityMapper);
    }

    @Bean
    public IOrderServicePort orderService() {
        return new OrderUseCase(orderPersistence(), orderProductPersistence());
    }
}