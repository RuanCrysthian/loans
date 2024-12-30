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

    // Executa o cálculo com a implementação real
    List<LoanType> loanTypes = calculator.calculate(inputDTO);

    // Verifica se todos os tipos de empréstimos esperados estão na lista
    Assertions.assertTrue(loanTypes.contains(LoanType.PERSONAL), "Personal loan should be available");
    Assertions.assertTrue(loanTypes.contains(LoanType.GUARANTEED), "Guaranteed loan should be available");
    Assertions.assertTrue(loanTypes.contains(LoanType.CONSIGNMENT), "Consignment loan should be available");
    Assertions.assertEquals(3, loanTypes.size(), "Should return exactly 3 loan types");
  }
}