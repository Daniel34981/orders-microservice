package com.ecommerce.orders.infraestructure.out.jpa.adapter;

import com.ecommerce.orders.domain.model.Role;
import com.ecommerce.orders.domain.spi.IRolePersistencePort;
import com.ecommerce.orders.infraestructure.out.jpa.mapper.RoleEntityMapper;
import com.ecommerce.orders.infraestructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Role findRoleByName(String roleName) {
        return roleEntityMapper.toRole(roleRepository.findByName(roleName));
    }
}
