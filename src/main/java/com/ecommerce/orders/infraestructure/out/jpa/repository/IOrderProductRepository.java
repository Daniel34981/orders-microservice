package com.ecommerce.orders.infraestructure.out.jpa.repository;

import com.ecommerce.orders.infraestructure.out.jpa.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderProductRepository extends JpaRepository<OrderProductEntity, Long> {
}
