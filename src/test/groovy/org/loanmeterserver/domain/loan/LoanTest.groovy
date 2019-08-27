package org.loanmeterserver.domain.loan


import spock.lang.Specification

class LoanTest extends Specification {

    def "should throw exception on null amount"() {
        when:
        new Loan(null)

        then:
        thrown(IllegalArgumentException)
    }
}
