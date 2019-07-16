package org.loanmeterserver.application.config;

import org.loanmeterserver.domain.client.ClientRepository;
import org.loanmeterserver.domain.loan.LoanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeansConfig {

    private final ClientRepository clientRepository;

    public DomainBeansConfig(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Bean
    public LoanFactory loanFactory() {
        return new LoanFactory(clientRepository);
    }
}
