package org.loanmeterserver.domain.loan;

import org.loanmeterserver.domain.shared.vo.Money;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public class LoanFactory {

    public Mono<Loan> createLoan(BigDecimal amountValue, String currencyCode) {
        return Mono.just(new Loan(new Money(amountValue, currencyCode)));
    }
}
