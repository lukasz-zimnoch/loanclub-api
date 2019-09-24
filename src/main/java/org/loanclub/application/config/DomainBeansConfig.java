package org.loanclub.application.config;

import org.loanclub.domain.loan.LoanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeansConfig {

    @Bean
    public LoanFactory loanFactory() {
        return new LoanFactory();
    }
}
