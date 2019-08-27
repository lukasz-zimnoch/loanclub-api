package org.loanmeterserver.application.loan;

import org.loanmeterserver.application.shared.base.BaseApplicationService;
import org.loanmeterserver.application.shared.exception.ApplicationException;
import org.loanmeterserver.application.shared.security.SecurityService;
import org.loanmeterserver.application.shared.validator.GenericValidator;
import org.loanmeterserver.domain.loan.LoanFactory;
import org.loanmeterserver.domain.loan.LoanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LoanApplicationService extends BaseApplicationService {

    private final LoanFactory loanFactory;

    private final LoanRepository loanRepository;

    public LoanApplicationService(ModelMapper mapper,
                                  GenericValidator validator,
                                  SecurityService securityService,
                                  LoanFactory loanFactory,
                                  LoanRepository loanRepository) {
        super(mapper, validator, securityService);
        this.loanFactory = loanFactory;
        this.loanRepository = loanRepository;
    }

    @PreAuthorize("hasAuthority('LOAN_REQUESTOR')")
    public Mono<LoanProjection> createLoan(LoanCreateData loanCreateData) {
        return Mono.just(validator.validate(loanCreateData))
                .zipWith(securityService.getAuthenticatedAccount())
                .flatMap(t -> loanFactory.createLoan(t.getT2(), t.getT1().getAmountValue(), t.getT1().getCurrencyCode()))
                .onErrorMap(throwable -> new ApplicationException(throwable.getMessage()))
                .flatMap(loanRepository::saveLoan)
                .map(savedLoan -> mapper.map(savedLoan, LoanProjection.class));
    }
}
