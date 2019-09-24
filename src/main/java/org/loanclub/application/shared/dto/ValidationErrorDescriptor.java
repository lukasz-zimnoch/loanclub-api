package org.loanclub.application.shared.dto;

import lombok.Value;

@Value
public class ValidationErrorDescriptor {

    private final String field;

    private final String message;
}
