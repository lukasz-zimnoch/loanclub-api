package org.loanmeterserver.domain.loan;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.loanmeterserver.domain.shared.base.BaseAggregateRoot;
import org.loanmeterserver.domain.shared.vo.Money;

@Getter
public class Loan extends BaseAggregateRoot {

    private final Money amount;

    Loan(Money amount) {
        Preconditions.checkArgument(amount != null, "Amount cannot be null");
        this.amount = amount;
    }
}
