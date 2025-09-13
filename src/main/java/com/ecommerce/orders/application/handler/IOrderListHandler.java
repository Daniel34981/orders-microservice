package com.ecommerce.orders.application.handler;

import com.ecommerce.orders.application.dto.OrderRequest;

public interface IOrderListHandler {

    void createOrder(OrderRequest orderRequest);

}
