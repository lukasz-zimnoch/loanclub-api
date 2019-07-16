package org.loanmeterserver.domain.loan;

import org.loanmeterserver.domain.client.ClientRepository;
import org.loanmeterserver.domain.shared.vo.Money;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public class LoanFactory {

    private final ClientRepository clientRepository;

    public LoanFactory(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Mono<Loan> createLoan(String clientId, BigDecimal amountValue, String currencyCode) {
        return clientRepository.findClientById(clientId)
                .switchIfEmpty(Mono.error(() ->
                        new IllegalArgumentException("Cannot create loan for non existing client: " + clientId)))
                .map(client -> new Loan(client, new Money(amountValue, currencyCode)));
    }
}
