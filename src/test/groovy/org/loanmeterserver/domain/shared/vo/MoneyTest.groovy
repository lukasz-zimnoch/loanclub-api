package org.loanmeterserver.domain.shared.vo

import spock.lang.Specification

import javax.money.CurrencyUnit
import javax.money.Monetary
import javax.money.UnknownCurrencyException

class MoneyTest extends Specification {

    private final CurrencyUnit currency = Monetary.getCurrency("USD")

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

    def "should throw exception on unknown currency code"() {
        when:
        new Money(BigDecimal.ZERO, "FAKE")

        then:
        thrown(UnknownCurrencyException)
    }

    def "should accept proper currency code"() {
        when:
        Money money = new Money(BigDecimal.ONE, "USD")

        then:
        money != null
    }
}
