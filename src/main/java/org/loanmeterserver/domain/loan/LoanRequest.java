package org.loanmeterserver.domain.loan;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.loanmeterserver.domain.shared.base.BaseAggregateRoot;
import org.loanmeterserver.domain.client.Client;
import org.loanmeterserver.domain.shared.vo.Money;

@Getter
public class LoanRequest extends BaseAggregateRoot {

    private final Client client;

    private final Money amount;

    public LoanRequest(Client client, Money amount) {
        Preconditions.checkArgument(client != null, "Client cannot be null");
        Preconditions.checkArgument(amount != null, "Amount cannot be null");
        this.client = client;
        this.amount = amount;
    }
}
