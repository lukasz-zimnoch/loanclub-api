package org.loanmeterserver.api.config;

import com.google.common.collect.ImmutableMap;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
@Order(-2)
class ExceptionHandlerConfig implements WebExceptionHandler {

    private final static Map<Class, HttpStatus> statusMappings = ImmutableMap.<Class, HttpStatus>builder()
            .put(IllegalArgumentException.class, HttpStatus.BAD_REQUEST) //TODO Change to validation exception
            .build();

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable t) {
        exchange.getResponse().setStatusCode(statusMappings.getOrDefault(t.getClass(), HttpStatus.INTERNAL_SERVER_ERROR));

        byte[] bytes = t.getMessage().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Flux.just(buffer));
    }
}
