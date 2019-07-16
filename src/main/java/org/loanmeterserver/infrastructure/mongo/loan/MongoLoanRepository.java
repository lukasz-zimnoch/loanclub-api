package org.loanmeterserver.infrastructure.mongo.loan;

import org.loanmeterserver.domain.loan.Loan;
import org.loanmeterserver.domain.shared.vo.AggregateId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoLoanRepository extends ReactiveMongoRepository<Loan, AggregateId> {

}
