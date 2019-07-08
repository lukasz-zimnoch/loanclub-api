package org.loanmeterserver.api.config;

import org.loanmeterserver.application.shared.exception.ValidationException;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
class CustomErrorAttributesConfig extends DefaultErrorAttributes {

    public CustomErrorAttributesConfig() {
        super(false);
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        final Throwable error = getError(request);
        final Map<String, Object> errorAttributes = super.getErrorAttributes(request, false);

        //TODO Refactor to strategy pattern
        if (error.getClass() == ValidationException.class) {
            errorAttributes.replace("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
            errorAttributes.replace("error", "Validation error");
            errorAttributes.replace("message", ((ValidationException)error).getErrors());
            return errorAttributes;
        }

        return errorAttributes;
    }
}
