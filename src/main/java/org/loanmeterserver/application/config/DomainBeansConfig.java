package org.loanmeterserver.application.config;

import org.loanmeterserver.domain.client.ClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeansConfig {

    @Bean
    public ClientFactory clientFactory() {
        return new ClientFactory();
    }
}
