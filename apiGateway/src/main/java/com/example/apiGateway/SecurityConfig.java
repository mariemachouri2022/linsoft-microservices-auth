package com.example.apiGateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity   // ✅ Obligatoire avec Spring Cloud Gateway (WebFlux)
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Pas de CSRF sur une API
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/public/**").permitAll()  // Routes publiques
                        .anyExchange().authenticated()           // Tout le reste nécessite un JWT
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt())   // ✅ Active la validation JWT Keycloak
                .build();
    }
}
