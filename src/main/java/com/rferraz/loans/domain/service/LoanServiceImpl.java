package com.rferraz.loans.domain.service;

import com.rferraz.loans.domain.dto.CustomerLoansInputDTO;
import com.rferraz.loans.domain.dto.CustomerLoansOutputDTO;
import com.rferraz.loans.domain.dto.LoanDTO;
import com.rferraz.loans.domain.enums.LoanType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

  public LoanServiceImpl() {
  }

  @Override
  public CustomerLoansOutputDTO getLoans(CustomerLoansInputDTO input) {
    CalculateCustomerLoans calculateCustomerLoans = new CalculateCustomerLoans(new LoanEligibilityChecker());
    List<LoanType> loansType = calculateCustomerLoans.calculate(input);
    List<LoanDTO> loans = loansType.stream()
      .map(this::mapToLoanDTO)
      .collect(Collectors.toList());
    return new CustomerLoansOutputDTO(input.name(), loans);
  }

  private LoanDTO mapToLoanDTO(LoanType loanType) {
    return new LoanDTO(loanType.name(), loanType.getInterestRate());
  }
}
