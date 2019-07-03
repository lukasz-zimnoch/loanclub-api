package org.loanmeterserver.infrastructure.mongo.client;

import org.loanmeterserver.domain.base.AggregateId;
import org.loanmeterserver.domain.client.Client;
import org.loanmeterserver.domain.client.ClientRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ClientRepositoryAdapter implements ClientRepository {

    private final MongoClientRepository mongoClientRepository;

    public ClientRepositoryAdapter(MongoClientRepository mongoClientRepository) {
        this.mongoClientRepository = mongoClientRepository;
    }

    @Override
    public Mono<Client> findClientById(AggregateId clientId) {
        return mongoClientRepository.findById(clientId);
    }

    @Override
    public Mono<Client> saveClient(Client client) {
        return mongoClientRepository.save(client);
    }
}
