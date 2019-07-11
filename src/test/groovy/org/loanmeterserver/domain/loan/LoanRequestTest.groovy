package org.loanmeterserver.domain.loan

import org.loanmeterserver.domain.client.Client
import org.loanmeterserver.domain.shared.vo.Money
import spock.lang.Specification

class LoanRequestTest extends Specification {

    public final Money amount = new Money(BigDecimal.TEN, Currency.getInstance("USD"))

    def "should throw exception on null client"() {
        when:
        new LoanRequest(null, amount)

        then:
        thrown(IllegalArgumentException)
    }

    def "should throw exception on null amount"() {
        when:
        Client client = Stub()
        new LoanRequest(client, null)

        then:
        thrown(IllegalArgumentException)
    }
}
