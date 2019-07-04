package org.loanmeterserver.application.client;

import org.loanmeterserver.domain.base.AggregateId;
import org.loanmeterserver.domain.client.Client;
import org.loanmeterserver.domain.client.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ClientApplicationService {

    private final ClientRepository clientRepository;

    private final ModelMapper mapper;

    public ClientApplicationService(ClientRepository clientRepository,
                                    ModelMapper mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    public Mono<ClientProjection> findClient(String clientId) {
        return clientRepository.findClientById(new AggregateId(clientId))
                .map(client -> mapper.map(client, ClientProjection.class));
    }

    public Mono<Void> createClient(ClientCreateData clientData) {
        Client client = new Client(clientData.getFirstName(), clientData.getSecondName());
        return clientRepository.saveClient(client).then();
    }
}
