package com.rferraz.loans.domain.service;

import com.rferraz.loans.domain.dto.CustomerLoansInputDTO;
import com.rferraz.loans.domain.enums.LoanType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class CalculateCustomerLoansTest {

  @Test
  void shouldReturnAllLoanTypesWhenAllConditionsAreMet() {
    LoanEligibilityChecker eligibilityChecker = new LoanEligibilityChecker();
    CalculateCustomerLoans calculator = new CalculateCustomerLoans(eligibilityChecker);

    CustomerLoansInputDTO inputDTO = new CustomerLoansInputDTO(25, "275.484.389-23", "Teste", new BigDecimal(4000), "SP");

    List<LoanType> loanTypes = calculator.calculate(inputDTO);

    Assertions.assertTrue(loanTypes.contains(LoanType.PERSONAL));
    Assertions.assertTrue(loanTypes.contains(LoanType.GUARANTEED));
    Assertions.assertTrue(loanTypes.contains(LoanType.CONSIGNMENT));
    Assertions.assertEquals(3, loanTypes.size());
  }
}