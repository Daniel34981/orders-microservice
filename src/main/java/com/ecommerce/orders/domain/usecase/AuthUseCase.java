package com.ecommerce.orders.domain.usecase;

import com.ecommerce.orders.domain.api.IAuthServicePort;
import com.ecommerce.orders.domain.exception.AuthException;
import com.ecommerce.orders.domain.model.Auth;
import com.ecommerce.orders.domain.model.User;
import com.ecommerce.orders.domain.model.UserAuth;
import com.ecommerce.orders.domain.spi.IAuthPersistencePort;
import com.ecommerce.orders.domain.spi.IUserPasswordEncoderPort;
import com.ecommerce.orders.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.ecommerce.orders.domain.constants.AuthErrorMessages.*;
import static com.ecommerce.orders.domain.constants.Roles.ADMIN;
import static com.ecommerce.orders.domain.constants.Roles.USER;

@AllArgsConstructor
public class AuthUseCase implements IAuthServicePort {

    private final IAuthPersistencePort authPersistencePort;
    private final IUserPasswordEncoderPort userPasswordEncoderPort;
    private final IUserPersistencePort userPersistencePort;

    @Override
    public Auth loginUser(UserAuth userAuth) {
        User user = userPersistencePort.getUserByEmail(userAuth.getEmail());
        if (user == null) {
            throw new AuthException(USERNAME_NOT_EXIST);
        }

        if (!userPasswordEncoderPort.matches(userAuth.getPassword(), user.getPassword())) {
            throw new AuthException(INVALID_PASSWORD);
        }

        List<String> authorities = new ArrayList<>();

        authorities.add(ROLE_AUTH + user.getRole().getName());

        // Obtener el ID del rol del usuario
        Long roleId = user.getRole().getId();
        System.out.println(user.toString());
        System.out.println(roleId);

        // Asignar autoridad según el ID del rol
        switch (roleId.intValue()) {
            case 1:
                authorities.add(ADMIN);
                break;
            case 2:
                authorities.add(USER);
                break;
            default:
                throw new AuthException(ROLE_NOT_EXIST);
        }

        Auth auth = authPersistencePort.loginUser(userAuth.getEmail(), authorities);
        auth.setMessage(LOGGED_IN);
        auth.setStatus(LOGGED_IN_SUCCESS);

        return auth;
    }
}