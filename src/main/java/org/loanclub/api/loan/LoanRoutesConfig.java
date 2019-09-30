package org.loanclub.api.loan;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class LoanRoutesConfig {

    private final LoanHandler loanHandler;

    public LoanRoutesConfig(LoanHandler loanHandler) {
        this.loanHandler = loanHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> loanRoutes() {
        return route()
                .nest(RequestPredicates.all(), builder -> builder
                        .POST("", contentType(APPLICATION_JSON), loanHandler::createLoan)
                        .GET("", loanHandler::findLoans))
                .build();
    }
}
