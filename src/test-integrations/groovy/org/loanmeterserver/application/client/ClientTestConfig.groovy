package org.loanmeterserver.application.client

import org.loanmeterserver.api.client.ClientHandler
import org.loanmeterserver.api.client.ClientRoutes
import org.loanmeterserver.api.config.CustomErrorAttributesConfig
import org.loanmeterserver.api.config.RoutesConfig
import org.loanmeterserver.application.shared.validator.GenericValidator
import org.loanmeterserver.infrastructure.mongo.client.ClientRepositoryAdapter
import org.modelmapper.ModelMapper
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import

@Import([
        CustomErrorAttributesConfig.class,
        RoutesConfig.class,
        ClientRoutes.class,
        ClientHandler.class,
        ClientApplicationService.class,
        ModelMapper.class,
        GenericValidator.class,
        ClientRepositoryAdapter.class
])
@TestConfiguration
class ClientTestConfig {

}
