package com.ecommerce.orders.infraestructure.iput.rest;

import com.ecommerce.orders.application.dto.OrderRequest;
import com.ecommerce.orders.application.handler.IOrderListHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderListHandler orderListHandler;

    @PostMapping("/CreateOrder")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequest orderRequest) {
        orderListHandler.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
