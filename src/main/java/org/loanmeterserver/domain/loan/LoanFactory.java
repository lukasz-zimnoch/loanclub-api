package org.loanmeterserver.domain.loan;

import org.loanmeterserver.domain.shared.vo.Account;
import org.loanmeterserver.domain.shared.vo.Money;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public class LoanFactory {

    public Mono<Loan> createLoan(Account account, BigDecimal amountValue, String currencyCode) {
        Money amount = new Money(amountValue, currencyCode);
        return Mono.just(new Loan(account, amount));
    }
}
