package org.loanmeterserver.domain.loan

import org.loanmeterserver.domain.shared.vo.Money
import spock.lang.Specification

class LoanFactoryTest extends Specification {

    private LoanFactory loanFactory

    def setup() {
        loanFactory = new LoanFactory()
    }

    def "should create loan"() {
        when:
        Loan loan = loanFactory.createLoan(BigDecimal.TEN, "USD").block()

        then:
        loan != null
        loan.getAmount() == new Money(BigDecimal.TEN,  "USD")
    }
}
