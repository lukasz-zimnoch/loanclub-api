package org.loanmeterserver.domain.shared.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.loanmeterserver.domain.shared.vo.AggregateId;

@Getter
@EqualsAndHashCode(of = "id")
public abstract class BaseAggregateRoot {

    private AggregateId id;

    private Long version;

    protected BaseAggregateRoot() {
        this.id = AggregateId.generate();
        this.version = 1L;
    }

    public String getIdValue() {
        return getId().getValue();
    }
}
