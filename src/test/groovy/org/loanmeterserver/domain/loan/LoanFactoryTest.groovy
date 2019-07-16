package org.loanmeterserver.domain.loan

import org.loanmeterserver.domain.client.Client
import org.loanmeterserver.domain.client.ClientRepository
import org.loanmeterserver.domain.shared.vo.Money
import reactor.core.publisher.Mono
import spock.lang.Specification

class LoanFactoryTest extends Specification {

    private LoanFactory loanFactory

    private ClientRepository clientRepository

    def setup() {
        clientRepository = Stub()

        loanFactory = new LoanFactory(clientRepository)
    }

    def "should create loan"() {
        given:
        String clientId = "clientId"
        Client client = Stub()
        clientRepository.findClientById(clientId) >> Mono.just(client)

        when:
        Loan loan = loanFactory.createLoan(clientId, BigDecimal.TEN, "USD").block()

        then:
        loan != null
        loan.getClient() == client
        loan.getAmount() == new Money(BigDecimal.TEN,  "USD")
    }

    def "should throw exception when client not exists"() {
        given:
        String clientId = "clientId"
        clientRepository.findClientById(clientId) >> Mono.empty()

        when:
        loanFactory.createLoan(clientId, BigDecimal.TEN, "USD").block()

        then:
        thrown(IllegalArgumentException)
    }
}
