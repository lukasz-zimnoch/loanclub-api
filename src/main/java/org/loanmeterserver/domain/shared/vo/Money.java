package org.loanmeterserver.domain.shared.vo;

import com.google.common.base.Preconditions;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;

@Value
public class Money {

    private final BigDecimal value;

    private final Currency currency;

    public Money(BigDecimal value, Currency currency) {
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0,
                "Value cannot be less than zero");
        Preconditions.checkArgument(currency != null, "Currency cannot be null");
        this.value = value;
        this.currency = currency;
    }
}
