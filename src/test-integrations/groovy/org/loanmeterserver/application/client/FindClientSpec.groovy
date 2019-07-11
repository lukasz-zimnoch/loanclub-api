package org.loanmeterserver.application.client

import org.loanmeterserver.domain.client.Client
import org.loanmeterserver.infrastructure.mongo.client.MongoClientRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import spock.lang.Specification

@WebFluxTest
@ContextConfiguration(classes = ClientTestConfig.class)
class FindClientSpec extends Specification {

    private WebTestClient webClient

    @Autowired
    private ApplicationContext context

    @SpringBean
    private MongoClientRepository mongoClientRepository = Mock()

    def setup() {
        webClient = WebTestClient.bindToApplicationContext(context).build()
    }

    def "should return 404 when client not exists"() {
        when:
        def response = webClient
                .get()
                .uri("/api/clients/id")
                .exchange()

        then:
        1 * mongoClientRepository.findById({id -> id.getValue() == 'id'}) >> {args -> Mono.empty()}

        and:
        response.expectStatus().isNotFound()
    }

    def "should return 200 and client projection in body"() {
        given:
        Client client = new Client("John", "Smith")

        when:
        def response = webClient
                .get()
                .uri("/api/clients/id")
                .exchange()

        then:
        1 * mongoClientRepository.findById({id -> id.getValue() == 'id'}) >> {args -> Mono.just(client)}

        and:
        response.expectStatus().isOk()

        and:
        ClientProjection responseBody = response
                .returnResult(ClientProjection.class)
                .getResponseBody()
                .blockFirst()

        responseBody.getId() == client.getIdValue()
        responseBody.getFirstName() == "John"
        responseBody.getSecondName() == "Smith"
    }
}
