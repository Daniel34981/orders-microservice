package com.ecommerce.orders.domain.usecase;

import com.ecommerce.orders.domain.api.IOrderServicePort;
import com.ecommerce.orders.domain.model.Order;
import com.ecommerce.orders.domain.model.OrderProduct;
import com.ecommerce.orders.domain.spi.IOrderPersistencePort;

import com.ecommerce.orders.domain.spi.IOrderProductPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IOrderProductPersistencePort orderProductPersistencePort;

    @Override
    @Transactional
    public void createOrder(Order order) {
        // 1) calcular total (opcional pero recomendado)
        double total = order.getProductList().stream()
                .mapToDouble(p -> p.getTotalPrice() * p.getQuantity())
                .sum();
        order.setTotalPrice(total);

        // 2) estado inicial
        order.setStatus(1L);   // si usas catálogo, adapta a FK Long

        // 3) guardar pedido y obtener ID
        Long orderId = orderPersistencePort.createOrder(order);

        // 4) recorrer y guardar cada item con ese orderId
        for (OrderProduct p : order.getProductList()) {
            p.setOrderId(orderId);
            double price = p.getTotalPrice();
            p.setTotalPrice(price * p.getQuantity());
            orderProductPersistencePort.saveOrderItem(p);
        }
    }
}
