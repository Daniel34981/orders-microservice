package com.ecommerce.orders.infraestructure.out.jpa.repository;

import com.ecommerce.orders.infraestructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
}
