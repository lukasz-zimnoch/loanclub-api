package org.loanmeterserver.domain.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public abstract class BaseAggregateRoot {

    private AggregateId id;

    private Long version;

    protected BaseAggregateRoot() {
        this.id = AggregateId.generate();
        this.version = 1L;
    }
}
