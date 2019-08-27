package org.loanmeterserver.application.loan

import org.loanmeterserver.api.config.CustomErrorAttributesConfig
import org.loanmeterserver.api.config.RoutesConfig
import org.loanmeterserver.api.loan.LoanHandler
import org.loanmeterserver.api.loan.LoanRoutesConfig
import org.loanmeterserver.application.shared.validator.GenericValidator
import org.loanmeterserver.domain.loan.LoanFactory
import org.loanmeterserver.infrastructure.mongo.loan.LoanRepositoryAdapter
import org.modelmapper.ModelMapper
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import

@Import([
        CustomErrorAttributesConfig.class,
        RoutesConfig.class,
        LoanRoutesConfig.class,
        LoanHandler.class,
        LoanApplicationService.class,
        ModelMapper.class,
        GenericValidator.class,
        LoanFactory.class,
        LoanRepositoryAdapter.class
])
@TestConfiguration
class LoanTestConfig {

}
