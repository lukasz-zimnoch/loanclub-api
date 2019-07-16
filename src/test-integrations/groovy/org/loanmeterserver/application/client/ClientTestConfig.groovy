package org.loanmeterserver.application.client

import org.loanmeterserver.api.client.ClientHandler
import org.loanmeterserver.api.client.ClientRoutesConfig
import org.loanmeterserver.api.config.CustomErrorAttributesConfig
import org.loanmeterserver.api.config.RoutesConfig
import org.loanmeterserver.application.shared.validator.GenericValidator
import org.loanmeterserver.infrastructure.mongo.client.ClientRepositoryAdapter
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
        ClientRoutesConfig.class,
        ClientHandler.class,
        ClientApplicationService.class,
        ModelMapper.class,
        GenericValidator.class,
        ClientRepositoryAdapter.class
])
@TestConfiguration
class ClientTestConfig {

    private DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

    @Bean
    RouterFunction<ServerResponse> loanRoutes() {
        return detachedMockFactory.Stub(RouterFunction)
    }
}
