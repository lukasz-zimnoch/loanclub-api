package org.loanmeterserver.api.config;

import com.google.common.collect.ImmutableMap;
import org.loanmeterserver.application.shared.exception.ValidationException;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;
import java.util.function.BiConsumer;

@Component
public class CustomErrorAttributesConfig extends DefaultErrorAttributes {

    private final Map<Class, BiConsumer<Throwable, Map<String, Object>>> ATTRIBUTE_PROCESSORS =
            ImmutableMap.<Class, BiConsumer<Throwable, Map<String, Object>>>builder()
                    .put(ValidationException.class, this::processValidationException)
                    .build();

    public CustomErrorAttributesConfig() {
        super(false);
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        final Throwable error = getError(request);
        final Map<String, Object> errorAttributes = super.getErrorAttributes(request, false);
        ATTRIBUTE_PROCESSORS.getOrDefault(error.getClass(), (e, ea) -> {}).accept(error, errorAttributes);
        return errorAttributes;
    }

    private void processValidationException(Throwable error, Map<String, Object> errorAttributes) {
        errorAttributes.replace("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorAttributes.replace("error", "Validation error");
        errorAttributes.replace("message", ((ValidationException)error).getErrors());
    }
}
