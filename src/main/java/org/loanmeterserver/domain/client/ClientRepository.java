package org.loanmeterserver.domain.client;

import org.loanmeterserver.domain.base.AggregateId;
import reactor.core.publisher.Mono;

public interface ClientRepository {

    Mono<Client> saveClient(Client client);

    Mono<Client> findClientById(AggregateId clientId);
}
