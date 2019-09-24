package org.loanclub.application.shared.validator;

import org.loanclub.application.shared.dto.ValidationErrorDescriptor;
import org.loanclub.application.shared.exception.ValidationException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GenericValidator {

    private final Validator validator;

    public GenericValidator(Validator validator) {
        this.validator = validator;
    }

    public <T> T validate(T input) {
        Set<ConstraintViolation<T>> violations = validator.validate(input);
        if(!violations.isEmpty()) {
            List<ValidationErrorDescriptor> errors = violations.stream()
                    .map(v -> new ValidationErrorDescriptor(v.getPropertyPath().toString(), v.getMessage()))
                    .collect(Collectors.toList());
            throw new ValidationException("Validation failed for input: " + input, errors);
        }
        return input;
    }
}


