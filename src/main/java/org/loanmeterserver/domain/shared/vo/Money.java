package org.loanmeterserver.domain.shared.vo;

import com.google.common.base.Preconditions;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import javax.money.Monetary;
import java.math.BigDecimal;

@Value
public class Money {

    private final BigDecimal value;

    private final String currencyCode;

    public Money(BigDecimal value, String currencyCode) {
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0,
                "Value cannot be less than zero");
        Preconditions.checkArgument(StringUtils.isNotBlank(currencyCode),
                "Currency code cannot be blank");
        Preconditions.checkNotNull(Monetary.getCurrency(currencyCode),
                "Invalid currency code");
        this.value = value;
        this.currencyCode = currencyCode;
    }
}
