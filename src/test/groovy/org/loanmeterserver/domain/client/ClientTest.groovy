package org.loanmeterserver.domain.client

import spock.lang.Specification

class ClientTest extends Specification {

    def "should throw exception on blank first name"() {
        when:
        new Client("", "Smith")

        then:
        thrown(IllegalArgumentException)
    }

    def "should throw exception on blank second name"() {
        when:
        new Client("John", "")

        then:
        thrown(IllegalArgumentException)
    }
}
