package org.loanmeterserver.domain.loan

import org.loanmeterserver.domain.client.Client
import org.loanmeterserver.domain.shared.vo.Money
import spock.lang.Specification

import javax.money.Monetary

class LoanTest extends Specification {

    private final Money amount = new Money(BigDecimal.TEN, Monetary.getCurrency("USD"))

    def "should throw exception on null client"() {
        when:
        new Loan(null, amount)

        then:
        thrown(IllegalArgumentException)
    }

    def "should throw exception on null amount"() {
        when:
        Client client = Stub()
        new Loan(client, null)

        then:
        thrown(IllegalArgumentException)
    }
}
