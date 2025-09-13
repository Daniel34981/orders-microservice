package com.ecommerce.orders.infraestructure.out.jpa.repository;

import com.ecommerce.orders.infraestructure.out.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
