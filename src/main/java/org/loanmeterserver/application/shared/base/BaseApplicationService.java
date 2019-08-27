package org.loanmeterserver.application.shared.base;

import org.loanmeterserver.application.shared.security.SecurityService;
import org.loanmeterserver.application.shared.validator.GenericValidator;
import org.modelmapper.ModelMapper;

public abstract class BaseApplicationService {

    protected final ModelMapper mapper;

    protected final GenericValidator validator;

    protected final SecurityService securityService;

    protected BaseApplicationService(ModelMapper mapper,
                                     GenericValidator validator,
                                     SecurityService securityService) {
        this.mapper = mapper;
        this.validator = validator;
        this.securityService = securityService;
    }
}
