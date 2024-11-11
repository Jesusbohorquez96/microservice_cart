package com.hexagonal.microservice_cart.infrastructure.configuration;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.hexagonal.microservice_cart.constants.ValidationConstants.*;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            HttpServletRequest request = getCurrentHttpRequest();
            if (request != null) {
                String authHeader = request.getHeader(AUTHORIZATION);
                if (authHeader != null && authHeader.startsWith(BEARER)) {
                    String token = authHeader.substring(SEVEN);
                    requestTemplate.header(AUTHORIZATION, BEARER + token);
                }
            }
        };
    }

    private HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }
}