package org.loanmeterserver.application.loan

import org.loanmeterserver.api.config.CustomErrorAttributesConfig
import org.loanmeterserver.api.config.RoutesConfig
import org.loanmeterserver.api.loan.LoanHandler
import org.loanmeterserver.api.loan.LoanRoutesConfig
import org.loanmeterserver.application.shared.validator.GenericValidator
import org.loanmeterserver.domain.loan.LoanFactory
import org.loanmeterserver.infrastructure.mongo.client.ClientRepositoryAdapter
import org.loanmeterserver.infrastructure.mongo.loan.LoanRepositoryAdapter
import org.modelmapper.ModelMapper
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import spock.mock.DetachedMockFactory

@Import([
        CustomErrorAttributesConfig.class,
        RoutesConfig.class,
        LoanRoutesConfig.class,
        LoanHandler.class,
        LoanApplicationService.class,
        ModelMapper.class,
        GenericValidator.class,
        LoanFactory.class,
        LoanRepositoryAdapter.class,
        ClientRepositoryAdapter.class
])
@TestConfiguration
class LoanTestConfig {

    private DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

    @Bean
    RouterFunction<ServerResponse> clientRoutes() {
        return detachedMockFactory.Stub(RouterFunction)
    }
}
