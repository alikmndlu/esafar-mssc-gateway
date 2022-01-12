package com.alikmndlu.gateway.config;

import com.alikmndlu.gateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("AUTH-SERVICE", r -> r.path("/api/auth/**").filters(f -> f.filter(filter)).uri("lb://AUTH-SERVICE"))
                .route("alert", r -> r.path("/alert/**").filters(f -> f.filter(filter)).uri("lb://alert"))
                .route("USER-SERVICE", r -> r.path("/api/users/**").filters(f -> f.filter(filter)).uri("lb://USER-SERVICE"))
                .route("echo", r -> r.path("/echo/**").filters(f -> f.filter(filter)).uri("lb://echo"))
                .route("hello", r -> r.path("/hello/**").filters(f -> f.filter(filter)).uri("lb://hello")).build();
    }

}

