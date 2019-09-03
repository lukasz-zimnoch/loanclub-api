package org.loanmeterserver.application.loan;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanProjection {

    private String id;

    private BigDecimal amountValue;

    private String amountCurrency;
}
