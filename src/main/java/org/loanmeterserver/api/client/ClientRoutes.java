package org.loanmeterserver.api.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class ClientRoutes {

    private final ClientHandler clientHandler;

    public ClientRoutes(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public RouterFunction<ServerResponse> routes() {
        return route()
                .nest(RequestPredicates.all(), builder -> builder
                        .GET("/{clientId}", clientHandler::findClient)
                        .POST("", clientHandler::createClient))
                .build();
    }
}
