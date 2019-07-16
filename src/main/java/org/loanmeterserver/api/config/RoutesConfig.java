package org.loanmeterserver.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoutesConfig {

    private final Map<String, RouterFunction<ServerResponse>> routes;

    public RoutesConfig(Map<String, RouterFunction<ServerResponse>> routes) {
        this.routes = routes;
    }

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route()
                .path("/api", builder -> builder
                        .path("/clients", () -> routes.get("clientRoutes"))
                        .path("/loans", () -> routes.get("loanRoutes")))
                .build();
    }
}


