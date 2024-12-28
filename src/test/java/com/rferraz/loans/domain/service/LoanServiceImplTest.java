package com.rferraz.loans.domain.service;

import com.rferraz.loans.domain.dto.CustomerLoansInputDTO;
import com.rferraz.loans.domain.dto.CustomerLoansOutputDTO;
import com.rferraz.loans.domain.dto.LoanDTO;
import com.rferraz.loans.domain.enums.LoanType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

class LoanServiceImplTest {

  @Test
  void shouldReturnLoans() {
    CustomerLoansInputDTO inputDTO = new CustomerLoansInputDTO(
      25,
      "275.484.389-23",
      "Teste",
      new BigDecimal(4000),
      "SP"
    );

    CalculateCustomerLoans calculateCustomerLoans = new CalculateCustomerLoans(new LoanEligibilityChecker());
    List<LoanType> loansType = calculateCustomerLoans.calculate(inputDTO);

    Assertions.assertEquals(3, loansType.size());

    List<LoanDTO> expectedLoans = loansType.stream()
      .map(loanType -> new LoanDTO(loanType.name(), loanType.getInterestRate()))
      .collect(Collectors.toList());

    CustomerLoansOutputDTO expectedResult = new CustomerLoansOutputDTO(
      inputDTO.name(),
      expectedLoans
    );

    LoanServiceImpl loanService = new LoanServiceImpl();
    CustomerLoansOutputDTO result = loanService.getLoans(inputDTO);

    Assertions.assertEquals(expectedResult, result);

  }

}