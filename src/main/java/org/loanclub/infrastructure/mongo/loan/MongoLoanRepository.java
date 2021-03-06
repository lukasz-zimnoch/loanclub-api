package org.loanclub.infrastructure.mongo.loan;

import org.loanclub.domain.loan.Loan;
import org.loanclub.domain.shared.vo.Account;
import org.loanclub.domain.shared.vo.AggregateId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MongoLoanRepository extends ReactiveMongoRepository<Loan, AggregateId> {

    Flux<Loan> findAllByAccount(Account account);
}
