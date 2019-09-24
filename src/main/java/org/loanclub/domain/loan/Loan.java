package org.loanclub.domain.loan;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.loanclub.domain.shared.base.BaseAggregateRoot;
import org.loanclub.domain.shared.vo.Account;
import org.loanclub.domain.shared.vo.Money;

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
