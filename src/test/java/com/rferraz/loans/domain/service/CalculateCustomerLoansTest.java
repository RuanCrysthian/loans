package com.rferraz.loans.domain.service;

import com.rferraz.loans.domain.dto.CustomerLoansInputDTO;
import com.rferraz.loans.domain.enums.LoanType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculateCustomerLoansTest {

  @Test
  void shouldReturnAllLoanTypesWhenAllConditionsAreMet() {
    LoanEligibilityChecker mockEligibilityChecker = mock(LoanEligibilityChecker.class);
    CustomerLoansInputDTO inputDTO = new CustomerLoansInputDTO(25, "275.484.389-23", "Teste", new BigDecimal(4000), "SP");

    // Mockar os métodos do LoanEligibilityChecker
    when(mockEligibilityChecker.isPersonalLoan(inputDTO)).thenReturn(true);
    when(mockEligibilityChecker.isGuaranteedLoan(inputDTO)).thenReturn(true);
    when(mockEligibilityChecker.isConsignmentLoan(inputDTO)).thenReturn(true);

    CalculateCustomerLoans calculator = new CalculateCustomerLoans(mockEligibilityChecker);

    List<LoanType> loanTypes = calculator.calculate(inputDTO);

    Assertions.assertTrue(loanTypes.contains(LoanType.PERSONAL));
    Assertions.assertTrue(loanTypes.contains(LoanType.GUARANTEED));
    Assertions.assertTrue(loanTypes.contains(LoanType.CONSIGNMENT));
    Assertions.assertEquals(3, loanTypes.size());
  }
}