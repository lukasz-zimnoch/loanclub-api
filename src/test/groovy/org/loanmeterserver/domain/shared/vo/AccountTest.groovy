package org.loanmeterserver.domain.shared.vo

import spock.lang.Specification

class AccountTest extends Specification {

    def "should throw exception on blank username"() {
        when:
        new Account("")

        then:
        thrown(IllegalArgumentException)
    }
}
