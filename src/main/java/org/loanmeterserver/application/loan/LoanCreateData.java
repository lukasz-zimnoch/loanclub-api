package org.loanmeterserver.application.loan;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class LoanCreateData {

    @NotNull
    @DecimalMin("0.00")
    private final BigDecimal amountValue;

    @NotBlank
    private final String amountCurrency;
}
