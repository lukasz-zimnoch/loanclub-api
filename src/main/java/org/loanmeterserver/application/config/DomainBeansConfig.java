package org.loanmeterserver.application.config;

import org.loanmeterserver.domain.loan.LoanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeansConfig {

    @Bean
    public LoanFactory loanFactory() {
        return new LoanFactory();
    }
}
