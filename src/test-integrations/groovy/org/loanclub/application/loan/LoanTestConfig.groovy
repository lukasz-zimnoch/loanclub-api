package org.loanclub.application.loan

import org.loanclub.api.config.CustomErrorAttributesConfig
import org.loanclub.api.config.RoutesConfig
import org.loanclub.api.config.SecurityConfig
import org.loanclub.api.loan.LoanHandler
import org.loanclub.api.loan.LoanRoutesConfig
import org.loanclub.application.shared.security.SecurityService
import org.loanclub.application.shared.validator.GenericValidator
import org.loanclub.domain.loan.LoanFactory
import org.loanclub.infrastructure.mongo.loan.LoanRepositoryAdapter
import org.modelmapper.ModelMapper
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import

@Import([
        CustomErrorAttributesConfig.class,
        RoutesConfig.class,
        SecurityConfig.class,
        LoanRoutesConfig.class,
        LoanHandler.class,
        LoanApplicationService.class,
        ModelMapper.class,
        GenericValidator.class,
        SecurityService.class,
        LoanFactory.class,
        LoanRepositoryAdapter.class
])
@TestConfiguration
class LoanTestConfig {

}
