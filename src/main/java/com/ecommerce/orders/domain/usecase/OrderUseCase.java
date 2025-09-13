package com.ecommerce.orders.domain.usecase;

import com.ecommerce.orders.domain.api.IOrderServicePort;
import com.ecommerce.orders.domain.model.Order;
import com.ecommerce.orders.domain.model.OrderProduct;
import com.ecommerce.orders.domain.spi.IOrderPersistencePort;

import com.ecommerce.orders.domain.spi.IOrderProductPersistencePort;
import com.ecommerce.orders.domain.spi.IStockPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IOrderProductPersistencePort orderProductPersistencePort;
    private final IStockPersistencePort stockPersistencePort;

    @Override
    @Transactional
    public void createOrder(Order order) {
        // 1) calcular total
        double total = order.getProductList().stream()
                .mapToDouble(p -> p.getTotalPrice() * p.getQuantity())
                .sum();
        order.setTotalPrice(total);
        order.setStatus(1L);

        // 2) validar y descontar stock (si falla, rollback de todo)
        order.getProductList().forEach(p ->
                stockPersistencePort.decrease(p.getProductId(), p.getQuantity())
        );

        // 3) guardar pedido y obtener ID
        Long orderId = orderPersistencePort.createOrder(order);

        // 4) guardar items
        for (OrderProduct p : order.getProductList()) {
            p.setOrderId(orderId);
            p.setTotalPrice(p.getTotalPrice() * p.getQuantity());
            orderProductPersistencePort.saveOrderItem(p);
        }
    }
}
