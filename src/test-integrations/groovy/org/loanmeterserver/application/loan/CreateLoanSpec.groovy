package org.loanmeterserver.application.loan


import org.loanmeterserver.domain.client.Client
import org.loanmeterserver.domain.shared.vo.AggregateId
import org.loanmeterserver.domain.shared.vo.Money
import org.loanmeterserver.infrastructure.mongo.client.MongoClientRepository
import org.loanmeterserver.infrastructure.mongo.loan.MongoLoanRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import reactor.core.publisher.Mono
import spock.lang.Specification
import org.springframework.http.HttpStatus

@WebFluxTest
@ContextConfiguration(classes = LoanTestConfig.class)
class CreateLoanSpec extends Specification {

    private WebTestClient webClient

    @Autowired
    private ApplicationContext context

    @SpringBean
    private MongoLoanRepository mongoLoanRepository = Mock()

    @SpringBean
    private MongoClientRepository mongoClientRepository = Stub()

    def setup() {
        webClient = WebTestClient.bindToApplicationContext(context).build()
    }

    def "should create loan and return 204"() {
        given:
        LoanCreateData requestBody = new LoanCreateData()
        requestBody.setClientId("clientId")
        requestBody.setAmountValue(BigDecimal.TEN)
        requestBody.setCurrencyCode("USD")

        and:
        Client client = Stub()
        mongoClientRepository.findById({(it as AggregateId).getValue() == "clientId"}) >> Mono.just(client)

        when:
        def response = webClient
                .post()
                .uri("/api/loans")
                .body(BodyInserters.fromObject(requestBody))
                .exchange()

        then:
        1 * mongoLoanRepository.save({loan ->
            loan.getId() != null
            loan.getClient() == client &&
            loan.getAmount() == new Money(BigDecimal.TEN, "USD") &&
            loan.getVersion() == 1L
        }) >> {args -> Mono.just(args[0])}

        and:
        response.expectStatus().isNoContent()
    }

    def "should return 422 when client id is invalid on create"() {
        given:
        LoanCreateData requestBody = new LoanCreateData()
        requestBody.setAmountValue(BigDecimal.TEN)
        requestBody.setCurrencyCode("USD")

        when:
        def response = webClient
                .post()
                .uri("/api/loans")
                .body(BodyInserters.fromObject(requestBody))
                .exchange()

        then:
        response.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
    }

    def "should return 422 when amount is invalid on create"() {
        given:
        LoanCreateData requestBody = new LoanCreateData()
        requestBody.setClientId("clientId")
        requestBody.setCurrencyCode("USD")

        when:
        def response = webClient
                .post()
                .uri("/api/loans")
                .body(BodyInserters.fromObject(requestBody))
                .exchange()

        then:
        response.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
    }

    def "should return 422 when currency code is invalid on create"() {
        given:
        LoanCreateData requestBody = new LoanCreateData()
        requestBody.setClientId("clientId")
        requestBody.setAmountValue(BigDecimal.TEN)

        when:
        def response = webClient
                .post()
                .uri("/api/loans")
                .body(BodyInserters.fromObject(requestBody))
                .exchange()

        then:
        response.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
    }
}
