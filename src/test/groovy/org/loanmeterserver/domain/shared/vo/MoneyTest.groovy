package org.loanmeterserver.domain.shared.vo

import spock.lang.Specification

class MoneyTest extends Specification {

    private Currency currency = Currency.getInstance("USD")

    def "should accept zero as amount"() {
        when:
        Money money = new Money(BigDecimal.ZERO, currency)

        then:
        money != null
    }

    def "should throw exception on amount less than zero"() {
        when:
        new Money(BigDecimal.valueOf(-1), currency)

        then:
        thrown(IllegalArgumentException)
    }

    def "should throw exception on null currency"() {
        when:
        new Money(BigDecimal.ZERO, null)

        then:
        thrown(IllegalArgumentException)
    }
}
