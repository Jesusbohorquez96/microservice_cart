package com.hexagonal.microservice_cart.infrastructure.output.jpa.mapper;

import com.hexagonal.microservice_cart.domain.model.Cart;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.hexagonal.microservice_cart.constants.ValidationConstants.*;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CartEntityMapper {

    CartEntity toEntity(Cart cart);

    Cart toDomain(CartEntity cartEntity);

    Cart toResponse(CartEntity cartEntity);
}
