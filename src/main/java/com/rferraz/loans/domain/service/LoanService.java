package com.rferraz.loans.domain.service;

import com.rferraz.loans.domain.dto.CustomerLoansInputDTO;
import com.rferraz.loans.domain.dto.CustomerLoansOutputDTO;

public interface LoanService {

  CustomerLoansOutputDTO getLoans(CustomerLoansInputDTO input);
}
