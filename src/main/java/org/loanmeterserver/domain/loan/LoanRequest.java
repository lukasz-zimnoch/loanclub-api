package org.loanmeterserver.domain.loan;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.loanmeterserver.domain.base.BaseAggregateRoot;
import org.loanmeterserver.domain.client.Client;
import org.loanmeterserver.domain.shared.vo.Money;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class LoanRequest extends BaseAggregateRoot {

    private final Client client;

    private final Money amount;
}
