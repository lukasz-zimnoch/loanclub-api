package org.loanmeterserver.application.shared.exception;

import lombok.Getter;
import org.loanmeterserver.application.shared.dto.ValidationErrorDescriptor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationException extends ApplicationException {

    private final List<ValidationErrorDescriptor> errors;

    public ValidationException(String message) {
        super(message);
        this.errors = new ArrayList<>();
    }

    public ValidationException(String message, List<ValidationErrorDescriptor> errors) {
        super(message);
        this.errors = errors;
    }
}
