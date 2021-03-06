package org.loanclub.domain.loan;

import org.loanclub.domain.shared.vo.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LoanRepository {

    Mono<Loan> saveLoan(Loan loan);

    Flux<Loan> findLoansByAccount(Account account);
}
