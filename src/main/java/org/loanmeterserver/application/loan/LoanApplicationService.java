package org.loanmeterserver.application.loan;

import org.loanmeterserver.application.shared.base.BaseApplicationService;
import org.loanmeterserver.application.shared.exception.ApplicationException;
import org.loanmeterserver.application.shared.validator.GenericValidator;
import org.loanmeterserver.domain.loan.LoanFactory;
import org.loanmeterserver.domain.loan.LoanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LoanApplicationService extends BaseApplicationService {

    private final LoanFactory loanFactory;

    private final LoanRepository loanRepository;

    public LoanApplicationService(ModelMapper mapper,
                                  GenericValidator validator,
                                  LoanFactory loanFactory,
                                  LoanRepository loanRepository) {
        super(mapper, validator);
        this.loanFactory = loanFactory;
        this.loanRepository = loanRepository;
    }

    public Mono<LoanProjection> createLoan(LoanCreateData loanCreateData) {
        validator.validate(loanCreateData);
        return loanFactory.createLoan(loanCreateData.getClientId(), loanCreateData.getAmountValue(),
                loanCreateData.getCurrencyCode())
                .onErrorMap(throwable -> new ApplicationException(throwable.getMessage()))
                .flatMap(loanRepository::saveLoan)
                .map(savedLoan -> mapper.map(savedLoan, LoanProjection.class));
    }
}
