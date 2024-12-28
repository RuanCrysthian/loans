package com.rferraz.loans.domain.dto;

import java.util.List;

public record CustomerLoansOutputDTO(
  String customer,
  List<LoanDTO> loans
) {
}
