package com.ecommerce.orders.domain.usecase;

import com.ecommerce.orders.domain.api.IUserServicePort;
import com.ecommerce.orders.domain.model.User;
import com.ecommerce.orders.domain.spi.IRolePersistencePort;
import com.ecommerce.orders.domain.spi.IUserPasswordEncoderPort;
import com.ecommerce.orders.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final IUserPasswordEncoderPort userPasswordEncoderPort;

    @Override
    public User getUserByEmail(String email) {
        return userPersistencePort.getUserByEmail(email);
    }
}
