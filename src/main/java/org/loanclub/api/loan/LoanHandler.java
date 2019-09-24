package org.loanclub.api.loan;

import org.loanclub.application.loan.LoanApplicationService;
import org.loanclub.application.loan.LoanCreateData;
import org.loanclub.application.loan.LoanProjection;
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

    Mono<ServerResponse> findLoans(ServerRequest serverRequest) {
        return serverRequest.queryParam("username")
                .map(loanApplicationService::findLoans)
                .map(loans -> ServerResponse.ok().body(loans, LoanProjection.class))
                .orElseGet(() ->  ServerResponse.badRequest().build());
    }
}
