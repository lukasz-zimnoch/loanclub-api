package org.loanmeterserver.api.loan;

import org.loanmeterserver.application.loan.LoanApplicationService;
import org.loanmeterserver.application.loan.LoanCreateData;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class LoanHandler {

    private final LoanApplicationService loanApplicationService;

    LoanHandler(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    Mono<ServerResponse> createLoan(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(LoanCreateData.class)
                .flatMap(loanApplicationService::createLoan)
                .flatMap(result -> ServerResponse.noContent().build());
    }
}
