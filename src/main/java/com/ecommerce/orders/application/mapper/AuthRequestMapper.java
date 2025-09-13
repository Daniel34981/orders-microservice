package com.ecommerce.orders.application.mapper;

import com.ecommerce.orders.application.dto.auth.AuthLoginRequestDto;
import com.ecommerce.orders.domain.model.UserAuth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AuthRequestMapper {
    default UserAuth toUserAuth(AuthLoginRequestDto userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        UserAuth userAuth = new UserAuth();

        userAuth.setEmail( userRequest.getUsername() );
        userAuth.setPassword( userRequest.getPassword() );

        return userAuth;
    }
}