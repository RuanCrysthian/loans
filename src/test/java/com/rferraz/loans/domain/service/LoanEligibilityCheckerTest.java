package com.rferraz.loans.domain.service;

import com.rferraz.loans.domain.dto.CustomerLoansInputDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class LoanEligibilityCheckerTest {

  @Test
  void shouldReturnTrueWhenIncomeIsGreaterThanOrEqualTo3000ForConsignmentLoan() {
    CustomerLoansInputDTO inputDTO = new CustomerLoansInputDTO(
      25,
      "275.484.389-23",
      "Teste",
      new BigDecimal(3000),
      "MG"
    );
    LoanEligibilityChecker eligibilityChecker = new LoanEligibilityChecker();

    boolean isConsignmentLoan = eligibilityChecker.isConsignmentLoan.test(inputDTO);

    Assertions.assertTrue(isConsignmentLoan);
  }

  @Test
  void shouldReturnFalseWhenIncomeIsLessThan3000ForConsignmentLoan() {
    CustomerLoansInputDTO inputDTO = new CustomerLoansInputDTO(
      25,
      "275.484.389-23",
      "Teste",
      new BigDecimal(2999),
      "MG"
    );
    LoanEligibilityChecker eligibilityChecker = new LoanEligibilityChecker();

    boolean isConsignmentLoan = eligibilityChecker.isConsignmentLoan.test(inputDTO);

    Assertions.assertFalse(isConsignmentLoan);
  }

  @Test
  void shouldReturnTrueForGuaranteedLoanWhenAllConditionsAreMet() {
    CustomerLoansInputDTO inputDTO = new CustomerLoansInputDTO(
      29,
      "275.484.389-23",
      "Teste",
      new BigDecimal(4000),
      "SP"
    );
    LoanEligibilityChecker eligibilityChecker = new LoanEligibilityChecker();

    boolean isGuaranteedLoan = eligibilityChecker.isGuaranteedLoan.test(inputDTO);

    Assertions.assertTrue(isGuaranteedLoan);
  }

  @Test
  void shouldReturnFalseForGuaranteedLoanWhenLocationIsDifferent() {
    CustomerLoansInputDTO inputDTO = new CustomerLoansInputDTO(
      29,
      "275.484.389-23",
      "Teste",
      new BigDecimal(4000),
      "MG"
    );
    LoanEligibilityChecker eligibilityChecker = new LoanEligibilityChecker();

    boolean isGuaranteedLoan = eligibilityChecker.isGuaranteedLoan.test(inputDTO);

    Assertions.assertFalse(isGuaranteedLoan);
  }

  @Test
  void shouldReturnFalseForGuaranteedLoanWhenIncomeIsOutOfRange() {
    CustomerLoansInputDTO inputDTO = new CustomerLoansInputDTO(
      29,
      "275.484.389-23",
      "Teste",
      new BigDecimal(2999),
      "SP"
    );
    LoanEligibilityChecker eligibilityChecker = new LoanEligibilityChecker();

    boolean isGuaranteedLoan = eligibilityChecker.isGuaranteedLoan.test(inputDTO);

    Assertions.assertFalse(isGuaranteedLoan);
  }

  @Test
  void shouldReturnFalseForGuaranteedLoanWhenAgeIsAboveThreshold() {
    CustomerLoansInputDTO inputDTO = new CustomerLoansInputDTO(
      31,
      "275.484.389-23",
      "Teste",
      new BigDecimal(4000),
      "SP"
    );
    LoanEligibilityChecker eligibilityChecker = new LoanEligibilityChecker();

    boolean isGuaranteedLoan = eligibilityChecker.isGuaranteedLoan.test(inputDTO);

    Assertions.assertFalse(isGuaranteedLoan);
  }

  @Test
  void shouldReturnTrueForPersonalLoanWhenIncomeIsBelow3000() {
    CustomerLoansInputDTO inputDTO = new CustomerLoansInputDTO(
      25,
      "275.484.389-23",
      "Teste",
      new BigDecimal(2999),
      "MG"
    );
    LoanEligibilityChecker eligibilityChecker = new LoanEligibilityChecker();

    boolean isPersonalLoan = eligibilityChecker.isPersonalLoan.test(inputDTO);

    Assertions.assertTrue(isPersonalLoan);
  }

  @Test
  void shouldReturnTrueForPersonalLoanWhenAllGuaranteedLoanConditionsAreMet() {
    CustomerLoansInputDTO inputDTO = new CustomerLoansInputDTO(
      29,
      "275.484.389-23",
      "Teste",
      new BigDecimal(4000),
      "SP"
    );
    LoanEligibilityChecker eligibilityChecker = new LoanEligibilityChecker();

    boolean isPersonalLoan = eligibilityChecker.isPersonalLoan.test(inputDTO);

    Assertions.assertTrue(isPersonalLoan);
  }

  @Test
  void shouldReturnFalseForPersonalLoanWhenIncomeExceeds5000() {
    CustomerLoansInputDTO inputDTO = new CustomerLoansInputDTO(
      29,
      "275.484.389-23",
      "Teste",
      new BigDecimal(5001),
      "SP"
    );
    LoanEligibilityChecker eligibilityChecker = new LoanEligibilityChecker();

    boolean isPersonalLoan = eligibilityChecker.isPersonalLoan.test(inputDTO);

    Assertions.assertFalse(isPersonalLoan);
  }
}
