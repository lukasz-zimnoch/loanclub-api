package org.loanmeterserver.application.loan;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class LoanCreateData {

    @NotBlank
    private String clientId;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal amountValue;

    @NotBlank
    private String currencyCode;
}
