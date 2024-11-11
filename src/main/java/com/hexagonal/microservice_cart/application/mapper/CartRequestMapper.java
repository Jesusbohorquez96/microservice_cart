package com.hexagonal.microservice_cart.application.mapper;

import com.hexagonal.microservice_cart.application.dto.CartRequest;
import com.hexagonal.microservice_cart.domain.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.hexagonal.microservice_cart.constants.ValidationConstants.*;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CartRequestMapper {

    Cart toCart(CartRequest cartRequest);
}
