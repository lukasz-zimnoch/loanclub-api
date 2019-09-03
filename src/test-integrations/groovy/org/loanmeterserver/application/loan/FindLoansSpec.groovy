package org.loanmeterserver.application.loan

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import org.loanmeterserver.domain.loan.Loan
import org.loanmeterserver.domain.loan.LoanFactory
import org.loanmeterserver.domain.shared.vo.Account
import org.loanmeterserver.infrastructure.mongo.loan.MongoLoanRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.ApplicationContext
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Specification

@WebFluxTest
@ContextConfiguration(classes = LoanTestConfig.class)
class FindLoansSpec extends Specification {

    private WebTestClient webClient

    @Autowired
    private ApplicationContext context

    @Autowired
    private LoanFactory loanFactory

    @SpringBean
    private ReactiveJwtDecoder reactiveJwtDecoder = Mock()

    @SpringBean
    private MongoLoanRepository mongoLoanRepository = Mock()

    private Jwt jwt

    def setup() {
        webClient = WebTestClient.bindToApplicationContext(context).build()
        setupMockJwt()
    }

    private void setupMockJwt() {
        jwt = Mock()
        jwt.getTokenValue() >> "tokenValue"
        jwt.getSubject() >> "username"
        jwt.getClaims() >> ImmutableMap.of("cognito:groups", ImmutableList.of("LOAN_REQUESTOR"))

        reactiveJwtDecoder.decode("tokenValue") >> Mono.just(jwt)
    }

    def "should find loans and return 200"() {
        given:
        Account account = new Account("username")

        and:
        Loan loan = loanFactory.createLoan(account, BigDecimal.TEN, "USD").block()

        when:
        def response = webClient
                .get()
                .uri("/api/loans?username=" + account.getUsername())
                .headers({headers -> headers.setBearerAuth(jwt.getTokenValue())})
                .exchange()

        then:
        1 * mongoLoanRepository.findAllByAccount(account) >> Flux.just(loan)

        and:
        response.expectStatus().isOk()

        and:
        LoanProjection responseBody = response
                .returnResult(LoanProjection.class)
                .getResponseBody()
                .blockFirst()

        responseBody.getId() == loan.getIdValue()
    }
}
