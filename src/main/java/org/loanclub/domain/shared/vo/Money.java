package org.loanclub.domain.shared.vo;

import com.google.common.base.Preconditions;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import javax.money.Monetary;
import java.math.BigDecimal;

@Value
public class Money {

    private final BigDecimal value;

    private final String currency;

    public Money(BigDecimal value, String currency) {
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0, "Value cannot be negative");
        Preconditions.checkArgument(StringUtils.isNotBlank(currency), "Currency cannot be blank");
        Preconditions.checkNotNull(Monetary.getCurrency(currency), "Unknown currency");
        this.value = value;
        this.currency = currency;
    }
}
