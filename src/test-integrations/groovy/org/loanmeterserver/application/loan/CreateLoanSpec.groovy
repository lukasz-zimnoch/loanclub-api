package org.loanmeterserver.application.loan

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import org.loanmeterserver.domain.shared.vo.Account
import org.loanmeterserver.domain.shared.vo.Money
import org.loanmeterserver.infrastructure.mongo.loan.MongoLoanRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import reactor.core.publisher.Mono
import spock.lang.Specification

@WebFluxTest
@ContextConfiguration(classes = LoanTestConfig.class)
class CreateLoanSpec extends Specification {

    private WebTestClient webClient

    @Autowired
    private ApplicationContext context

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

    def "should create loan and return 204"() {
        given:
        LoanCreateData requestBody = new LoanCreateData(BigDecimal.TEN, "USD")

        when:
        def response = webClient
                .post()
                .uri("/api/loans")
                .headers({headers -> headers.setBearerAuth(jwt.getTokenValue())})
                .body(BodyInserters.fromObject(requestBody))
                .exchange()

        then:
        1 * mongoLoanRepository.save({loan ->
            loan.getId() != null &&
            loan.getAccount() == new Account("username") &&
            loan.getAmount() == new Money(BigDecimal.TEN, "USD") &&
            loan.getVersion() == 1L
        }) >> {args -> Mono.just(args[0])}

        and:
        response.expectStatus().isNoContent()
    }

    def "should return 422 when amount is invalid on create"() {
        given:
        LoanCreateData requestBody = new LoanCreateData(null, "USD")

        when:
        def response = webClient
                .post()
                .uri("/api/loans")
                .headers({headers -> headers.setBearerAuth(jwt.getTokenValue())})
                .body(BodyInserters.fromObject(requestBody))
                .exchange()

        then:
        response.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
    }

    def "should return 422 when currency code is invalid on create"() {
        given:
        LoanCreateData requestBody = new LoanCreateData(BigDecimal.TEN, null)

        when:
        def response = webClient
                .post()
                .uri("/api/loans")
                .headers({headers -> headers.setBearerAuth(jwt.getTokenValue())})
                .body(BodyInserters.fromObject(requestBody))
                .exchange()

        then:
        response.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
    }
}
