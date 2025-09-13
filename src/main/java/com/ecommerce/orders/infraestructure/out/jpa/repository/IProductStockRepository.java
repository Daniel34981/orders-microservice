package com.ecommerce.orders.infraestructure.out.jpa.repository;

import com.ecommerce.orders.infraestructure.out.jpa.entity.ProductStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductStockRepository extends JpaRepository<ProductStockEntity, Long> {}
