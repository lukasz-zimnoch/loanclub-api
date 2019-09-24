package org.loanclub.domain.shared.vo;

import lombok.Value;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Value
public class AggregateId implements Serializable {

    private final String value;

    public AggregateId(String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static AggregateId generate() {
        return new AggregateId(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return value;
    }
}
