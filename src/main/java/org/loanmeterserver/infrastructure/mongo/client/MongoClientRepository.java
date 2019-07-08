package org.loanmeterserver.infrastructure.mongo.client;

import org.loanmeterserver.domain.shared.vo.AggregateId;
import org.loanmeterserver.domain.client.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoClientRepository extends ReactiveMongoRepository<Client, AggregateId> {

}
