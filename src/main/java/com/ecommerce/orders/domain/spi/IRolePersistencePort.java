package com.ecommerce.orders.domain.spi;

import com.ecommerce.orders.domain.model.Role;

public interface IRolePersistencePort {
    Role findRoleByName(String roleName);
}
