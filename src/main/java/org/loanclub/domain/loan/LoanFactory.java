package org.loanclub.domain.loan;

import org.loanclub.domain.shared.vo.Account;
import org.loanclub.domain.shared.vo.Money;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public class LoanFactory {

    public Mono<Loan> createLoan(Account account, BigDecimal amountValue, String currencyCode) {
        Money amount = new Money(amountValue, currencyCode);
        return Mono.just(new Loan(account, amount));
    }
}
