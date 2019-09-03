package org.loanmeterserver.domain.shared.vo

import spock.lang.Specification

import javax.money.UnknownCurrencyException

class MoneyTest extends Specification {

    def "should accept zero as value"() {
        when:
        Money money = new Money(BigDecimal.ZERO, "USD")

        then:
        money != null
    }

    def "should throw exception on negative value"() {
        when:
        new Money(BigDecimal.valueOf(-1), "USD")

        then:
        thrown(IllegalArgumentException)
    }

    def "should throw exception on null currency"() {
        when:
        new Money(BigDecimal.ZERO, null)

        then:
        thrown(IllegalArgumentException)
    }

    def "should throw exception on unknown currency"() {
        when:
        new Money(BigDecimal.ZERO, "FAKE")

        then:
        thrown(UnknownCurrencyException)
    }

    def "should accept proper currency"() {
        when:
        Money money = new Money(BigDecimal.ONE, "USD")

        then:
        money != null
    }
}
