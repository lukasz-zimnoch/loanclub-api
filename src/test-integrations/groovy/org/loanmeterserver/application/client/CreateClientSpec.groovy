package org.loanmeterserver.application.client

import org.loanmeterserver.infrastructure.mongo.client.MongoClientRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import reactor.core.publisher.Mono
import spock.lang.Specification

@WebFluxTest
@ContextConfiguration(classes = ClientTestConfig.class)
class CreateClientSpec extends Specification {

    private WebTestClient webClient

    @Autowired
    private ApplicationContext context

    @SpringBean
    private MongoClientRepository mongoClientRepository = Mock()

    def setup() {
        webClient = WebTestClient.bindToApplicationContext(context).build()
    }

    def "should create client and return 204"() {
        given:
        ClientCreateData requestBody = new ClientCreateData()
        requestBody.setFirstName("John")
        requestBody.setSecondName("Smith")

        when:
        def response = webClient
                .post()
                .uri("/api/clients")
                .body(BodyInserters.fromObject(requestBody))
                .exchange()

        then:
        1 * mongoClientRepository.save({client ->
            client.getId() != null
            client.getFirstName() == "John" &&
            client.getSecondName() == "Smith" &&
            client.getVersion() == 1L
        }) >> {args -> Mono.just(args[0])}

        and:
        response.expectStatus().isNoContent()
    }

    def "should return 422 when first name is invalid on create"() {
        given:
        ClientCreateData requestBody = new ClientCreateData()
        requestBody.setSecondName("Smith")

        when:
        def response = webClient
                .post()
                .uri("/api/clients")
                .body(BodyInserters.fromObject(requestBody))
                .exchange()

        then:
        response.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
    }

    def "should return 422 when second name is invalid on create"() {
        given:
        ClientCreateData requestBody = new ClientCreateData()
        requestBody.setFirstName("John")

        when:
        def response = webClient
                .post()
                .uri("/api/clients")
                .body(BodyInserters.fromObject(requestBody))
                .exchange()

        then:
        response.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
    }
}
