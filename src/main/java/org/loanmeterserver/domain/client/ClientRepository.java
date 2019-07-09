package org.loanmeterserver.domain.client;

import reactor.core.publisher.Mono;

public interface ClientRepository {

    Mono<Client> saveClient(Client client);

    Mono<Client> findClientById(String clientId);
}
