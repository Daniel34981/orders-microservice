package com.ecommerce.orders.infraestructure.out.jpa.adapter;

import com.ecommerce.orders.domain.model.User;
import com.ecommerce.orders.domain.spi.IUserPersistencePort;
import com.ecommerce.orders.infraestructure.out.jpa.mapper.UserEntityMapper;
import com.ecommerce.orders.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User getUserByEmail(String email) {
        return userEntityMapper.toUser(userRepository.findByEmail(email));
    }

}
