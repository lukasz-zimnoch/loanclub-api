package org.loanmeterserver.application.client;

import org.loanmeterserver.application.shared.base.BaseApplicationService;
import org.loanmeterserver.application.shared.validator.GenericValidator;
import org.loanmeterserver.domain.client.Client;
import org.loanmeterserver.domain.client.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ClientApplicationService extends BaseApplicationService {

    private final ClientRepository clientRepository;

    public ClientApplicationService(ModelMapper mapper,
                                    GenericValidator validator,
                                    ClientRepository clientRepository) {
        super(mapper, validator);
        this.clientRepository = clientRepository;
    }

    public Mono<ClientProjection> findClient(String clientId) {
        return clientRepository.findClientById(clientId)
                .map(client -> mapper.map(client, ClientProjection.class));
    }

    public Mono<ClientProjection> createClient(ClientCreateData clientData) {
        validator.validate(clientData);
        Client client = new Client(clientData.getFirstName(), clientData.getSecondName());
        return clientRepository.saveClient(client)
                .map(savedClient -> mapper.map(savedClient, ClientProjection.class));
    }
}
