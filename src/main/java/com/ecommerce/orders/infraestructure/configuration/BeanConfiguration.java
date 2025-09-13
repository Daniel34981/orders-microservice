package com.ecommerce.orders.infraestructure.configuration;

import com.ecommerce.orders.domain.api.IAuthServicePort;
import com.ecommerce.orders.domain.api.IOrderServicePort;
import com.ecommerce.orders.domain.spi.*;
import com.ecommerce.orders.domain.usecase.AuthUseCase;
import com.ecommerce.orders.domain.usecase.OrderUseCase;
import com.ecommerce.orders.infraestructure.out.jpa.adapter.*;
import com.ecommerce.orders.infraestructure.out.jpa.mapper.IOrderEntityMapper;
import com.ecommerce.orders.infraestructure.out.jpa.mapper.IOrderProductEntityMapper;
import com.ecommerce.orders.infraestructure.out.jpa.mapper.RoleEntityMapper;
import com.ecommerce.orders.infraestructure.out.jpa.mapper.UserEntityMapper;
import com.ecommerce.orders.infraestructure.out.jpa.repository.*;
import com.ecommerce.orders.infraestructure.passwordencode.PasswordEncoderAdapter;
import com.ecommerce.orders.infraestructure.security.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderProductRepository orderProductRepository;
    private final IOrderProductEntityMapper orderProductEntityMapper;
    private final IProductStockRepository stockRepository;
    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;
    private final JwtUtils jwtUtils;

    @Bean
    public IOrderPersistencePort orderPersistence() {
        return new OrderJpaAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public IOrderProductPersistencePort orderProductPersistence()  {
        return new OrderProductJpaAdapter(orderProductRepository, orderProductEntityMapper);
    }

    @Bean
    public IStockPersistencePort stockPersistence() {
        return new StockJpaAdapter(stockRepository);
    }

    @Bean
    public IOrderServicePort orderService() {
        return new OrderUseCase(orderPersistence(), orderProductPersistence(), stockPersistence());
    }
    @Bean
    public IUserPasswordEncoderPort userPasswordEncoderPort() {
        return new PasswordEncoderAdapter();
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IAuthPersistencePort authPersistencePort() {
        return new AuthJpaAdapter(userRepository ,jwtUtils);
    }

    @Bean
    public IAuthServicePort authServicePort() {
        return new AuthUseCase(authPersistencePort(), userPasswordEncoderPort(), userPersistencePort());
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }
}