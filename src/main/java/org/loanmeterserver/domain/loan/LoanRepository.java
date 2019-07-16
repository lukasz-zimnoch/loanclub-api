package org.loanmeterserver.domain.loan;

import reactor.core.publisher.Mono;

public interface LoanRepository {

    Mono<Loan> saveLoan(Loan loan);
}
