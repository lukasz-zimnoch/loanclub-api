package org.loanclub.domain.loan

import org.loanclub.domain.shared.vo.Account
import org.loanclub.domain.shared.vo.Money
import spock.lang.Specification

class LoanFactoryTest extends Specification {

    private LoanFactory loanFactory

    def setup() {
        loanFactory = new LoanFactory()
    }

    def "should create loan"() {
        when:
        Loan loan = loanFactory.createLoan(new Account("username"), BigDecimal.TEN, "USD").block()

        then:
        loan != null
        loan.getAccount() == new Account("username")
        loan.getAmount() == new Money(BigDecimal.TEN,  "USD")
        loan.getVersion() == 1L
    }
}
