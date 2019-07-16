package org.loanmeterserver.api.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ClientRoutesConfig {

    private final ClientHandler clientHandler;

    public ClientRoutesConfig(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> clientRoutes() {
        return route()
                .nest(RequestPredicates.all(), builder -> builder
                        .GET("/{clientId}", clientHandler::findClient)
                        .POST("", clientHandler::createClient))
                .build();
    }
}
