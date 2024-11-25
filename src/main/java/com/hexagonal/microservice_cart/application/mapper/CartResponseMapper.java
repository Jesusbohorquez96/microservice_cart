package com.hexagonal.microservice_cart.application.mapper;

import com.hexagonal.microservice_cart.application.dto.CartResponse;
import com.hexagonal.microservice_cart.domain.model.Cart;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static com.hexagonal.microservice_cart.constants.ValidationConstants.*;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {Cart.class})
public interface CartResponseMapper {

    @Mapping(source = "articleId", target = "articleId")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "creationDate", target = "creationDate")
    @Mapping(source = "updateDate", target = "updateDate")
    CartResponse toCartResponse(CartEntity cartEntity);
}
