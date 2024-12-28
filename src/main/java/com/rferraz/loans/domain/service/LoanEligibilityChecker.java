package com.rferraz.loans.domain.service;

import com.rferraz.loans.domain.dto.CustomerLoansInputDTO;

import java.math.BigDecimal;

public class LoanEligibilityChecker {

  public boolean isConsignmentLoan(CustomerLoansInputDTO input) {
    return input.income().compareTo(LoanConstants.MIN_INCOME_TO_GET_LOAN) >= 0;
  }

  public boolean isGuaranteedLoan(CustomerLoansInputDTO input) {
    return isIncomeBetween(input.income()) &&
      LoanConstants.LOCATION_TO_GET_LOAN.equals(input.location()) &&
      input.age() < LoanConstants.AGE_TO_GET_LOAN;
  }

  public boolean isPersonalLoan(CustomerLoansInputDTO input) {
    return input.income().compareTo(LoanConstants.MIN_INCOME_TO_GET_LOAN) <= 0 ||
      (isIncomeBetween(input.income()) &&
        LoanConstants.LOCATION_TO_GET_LOAN.equalsIgnoreCase(input.location()) &&
        input.age() < LoanConstants.AGE_TO_GET_LOAN);
  }

  private boolean isIncomeBetween(BigDecimal income) {
    return income.compareTo(LoanConstants.MIN_INCOME_TO_GET_LOAN) >= 0 && income.compareTo(LoanConstants.MAX_INCOME_GET_LOAN) <= 0;
  }

  private static class LoanConstants {
    public static final BigDecimal MIN_INCOME_TO_GET_LOAN = BigDecimal.valueOf(3000);
    public static final BigDecimal MAX_INCOME_GET_LOAN = BigDecimal.valueOf(5000);
    public static final int AGE_TO_GET_LOAN = 30;
    public static final String LOCATION_TO_GET_LOAN = "SP";
  }
}

