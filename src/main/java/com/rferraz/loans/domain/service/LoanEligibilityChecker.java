package com.rferraz.loans.domain.service;

import com.rferraz.loans.domain.dto.CustomerLoansInputDTO;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class LoanEligibilityChecker {

  private final Predicate<BigDecimal> isIncomeWithinLoanRange = LoanConstants::isIncomeWithinRange;
  private final Predicate<String> isValidLocation = LoanConstants::isValidLocation;
  private final Predicate<Integer> isUnderAgeLimit = LoanConstants::isUnderAgeLimit;

  public Predicate<CustomerLoansInputDTO> isConsignmentLoan =
    input -> input.income().compareTo(LoanConstants.MIN_INCOME_TO_GET_LOAN) >= 0;

  public Predicate<CustomerLoansInputDTO> isGuaranteedLoan =
    input -> isIncomeWithinLoanRange.test(input.income()) &&
      isValidLocation.test(input.location()) &&
      isUnderAgeLimit.test(input.age());

  public Predicate<CustomerLoansInputDTO> isPersonalLoan =
    input -> input.income().compareTo(LoanConstants.MIN_INCOME_TO_GET_LOAN) <= 0 ||
      (isIncomeWithinLoanRange.test(input.income()) &&
        isValidLocation.test(input.location()) &&
        isUnderAgeLimit.test(input.age()));
}

