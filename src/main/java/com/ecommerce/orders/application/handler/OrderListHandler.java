package com.ecommerce.orders.application.handler;

import com.ecommerce.orders.application.dto.OrderRequest;
import com.ecommerce.orders.application.mapper.request.OrderRequestMapper;
import com.ecommerce.orders.domain.api.IOrderServicePort;
import com.ecommerce.orders.domain.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderListHandler implements IOrderListHandler {

    private final IOrderServicePort orderServicePort;
    private final OrderRequestMapper orderRequestMapper;

    @Override
    public void createOrder(OrderRequest orderRequest) {
        Order order = orderRequestMapper.toOrder(orderRequest);
        orderServicePort.createOrder(order);
    }
}
