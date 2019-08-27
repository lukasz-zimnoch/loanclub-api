package org.loanmeterserver.domain.loan

import org.loanmeterserver.domain.shared.vo.Account
import org.loanmeterserver.domain.shared.vo.Money
import spock.lang.Specification

class LoanTest extends Specification {

    def "should throw exception on null account"() {
        when:
        new Loan(null, new Money(BigDecimal.TEN, "PLN"))

        then:
        thrown(IllegalArgumentException)
    }

    def "should throw exception on null amount"() {
        when:
        new Loan(new Account("username"), null)

        then:
        thrown(IllegalArgumentException)
    }
}
