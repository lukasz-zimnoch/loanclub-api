package org.loanclub.infrastructure.mongo.loan;

import org.loanclub.domain.loan.Loan;
import org.loanclub.domain.loan.LoanRepository;
import org.loanclub.domain.shared.vo.Account;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class LoanRepositoryAdapter implements LoanRepository {

    private final MongoLoanRepository mongoLoanRepository;

    public LoanRepositoryAdapter(MongoLoanRepository mongoLoanRepository) {
        this.mongoLoanRepository = mongoLoanRepository;
    }

    @Override
    public Mono<Loan> saveLoan(Loan loan) {
        return mongoLoanRepository.save(loan);
    }

    @Override
    public Flux<Loan> findLoansByAccount(Account account) {
        return mongoLoanRepository.findAllByAccount(account);
    }
}
