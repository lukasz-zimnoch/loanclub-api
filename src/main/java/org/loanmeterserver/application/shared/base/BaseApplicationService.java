package org.loanmeterserver.application.shared.base;

import org.loanmeterserver.application.shared.validator.GenericValidator;
import org.modelmapper.ModelMapper;

public abstract class BaseApplicationService {

    protected final ModelMapper mapper;

    protected final GenericValidator validator;

    protected BaseApplicationService(ModelMapper mapper,
                                     GenericValidator validator) {
        this.mapper = mapper;
        this.validator = validator;
    }
}
