package org.loanmeterserver.domain.shared.vo;

import com.google.common.base.Preconditions;
import lombok.Value;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.math.BigDecimal;

@Value
public class Money {

    private final BigDecimal value;

    private final CurrencyUnit currency;

    public Money(BigDecimal value, CurrencyUnit currency) {
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0,
                "Value cannot be less than zero");
        Preconditions.checkArgument(currency != null, "Currency cannot be null");
        this.value = value;
        this.currency = currency;
    }

    public Money(BigDecimal value, String currencyCode) {
        this(value, Monetary.getCurrency(currencyCode));
    }
}
