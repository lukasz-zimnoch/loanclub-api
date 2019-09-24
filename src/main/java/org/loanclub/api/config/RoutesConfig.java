package org.loanclub.api.config;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

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
                        .path("/loans", () -> routes.get("loanRoutes")))
                .build();
    }
}


