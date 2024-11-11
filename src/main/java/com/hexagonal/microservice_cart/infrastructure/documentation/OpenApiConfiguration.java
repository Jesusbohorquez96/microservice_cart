package com.hexagonal.microservice_cart.infrastructure.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.hexagonal.microservice_cart.constants.ValidationConstants.*;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenApi(@Value(APP_DESCRIPTION) String appDescription,
                                 @Value(APP_VERSION) String appVersion) {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(TITLE)
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService(TERMS_OF_SERVICE_URL)
                        .license(new License().name(LICENSE_NAME).url(LICENSE_URL)));
    }
}
