package org.loanmeterserver.api.config;

import org.loanmeterserver.api.client.ClientRoutes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoutesConfig {

    private final ClientRoutes clientRoutes;

    public RoutesConfig(ClientRoutes clientRoutes) {
        this.clientRoutes = clientRoutes;
    }

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route()
                .path("/api", builder -> builder
                        .path("/clients", clientRoutes::routes))
                .build();
    }
}


