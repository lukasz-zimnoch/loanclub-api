package org.loanmeterserver.api.client;

import org.loanmeterserver.application.client.ClientApplicationService;
import org.loanmeterserver.application.client.ClientCreateData;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ClientHandler {

    private final ClientApplicationService clientApplicationService;

    public ClientHandler(ClientApplicationService clientApplicationService) {
        this.clientApplicationService = clientApplicationService;
    }

    Mono<ServerResponse> findClient(ServerRequest serverRequest) {
        String clientId = serverRequest.pathVariable("clientId");
        return clientApplicationService.findClient(clientId)
                .map(BodyInserters::fromObject)
                .flatMap(body -> ServerResponse.ok().body(body))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    Mono<ServerResponse> createClient(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ClientCreateData.class)
                .flatMap(clientApplicationService::createClient)
                .flatMap(result -> ServerResponse.noContent().build());
    }
}
