package org.loanmeterserver.domain.loan;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.loanmeterserver.domain.shared.base.BaseAggregateRoot;
import org.loanmeterserver.domain.shared.vo.Account;
import org.loanmeterserver.domain.shared.vo.Money;

@Getter
public class Loan extends BaseAggregateRoot {

    private final Account account;

    private final Money amount;

    Loan(Account account, Money amount) {
        Preconditions.checkArgument(account != null, "Account cannot be null");
        Preconditions.checkArgument(amount != null, "Amount cannot be null");
        this.account = account;
        this.amount = amount;
    }
}
