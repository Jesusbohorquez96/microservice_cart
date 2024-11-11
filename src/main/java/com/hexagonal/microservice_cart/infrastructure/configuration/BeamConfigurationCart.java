package com.hexagonal.microservice_cart.infrastructure.configuration;

import com.hexagonal.microservice_cart.domain.api.ICartServicePort;
import com.hexagonal.microservice_cart.domain.spi.ICartPersistencePort;
import com.hexagonal.microservice_cart.domain.usecase.CartUseCase;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.adapter.CartJpaAdapter;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.mapper.CartEntityMapper;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class BeamConfigurationCart {

    private final ICartRepository cartRepository;
    private final CartEntityMapper cartEntityMapper;

    @Bean
    public ICartPersistencePort cartPersistencePort() {
        return new CartJpaAdapter(cartRepository, cartEntityMapper);
    }

    @Bean
    public ICartServicePort cartServicePort() {
        return new CartUseCase(cartPersistencePort()) {
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
