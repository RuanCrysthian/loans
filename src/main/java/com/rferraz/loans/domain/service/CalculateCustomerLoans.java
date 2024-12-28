package com.rferraz.loans.domain.service;

import com.rferraz.loans.domain.dto.CustomerLoansInputDTO;
import com.rferraz.loans.domain.enums.LoanType;

import java.util.ArrayList;
import java.util.List;

public class CalculateCustomerLoans {

  private final LoanEligibilityChecker eligibilityChecker;

  public CalculateCustomerLoans(LoanEligibilityChecker eligibilityChecker) {
    this.eligibilityChecker = eligibilityChecker;
  }

  public List<LoanType> calculate(CustomerLoansInputDTO input) {
    LoanEligibilityChecker eligibilityChecker = new LoanEligibilityChecker();

    List<LoanType> loans = new ArrayList<>();
    if (eligibilityChecker.isPersonalLoan(input)) loans.add(LoanType.PERSONAL);
    if (eligibilityChecker.isGuaranteedLoan(input)) loans.add(LoanType.GUARANTEED);
    if (eligibilityChecker.isConsignmentLoan(input)) loans.add(LoanType.CONSIGNMENT);

    return loans;
  }
}