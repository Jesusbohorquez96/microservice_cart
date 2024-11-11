package com.hexagonal.microservice_cart.infrastructure.adapters.jwtentrypoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import static com.hexagonal.microservice_cart.constants.ValidationConstants.*;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType(JSON);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().println(ERROR_JWT);
    }
}
