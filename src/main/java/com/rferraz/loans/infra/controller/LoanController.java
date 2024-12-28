package com.rferraz.loans.infra.controller;

import com.rferraz.loans.domain.dto.CustomerLoansInputDTO;
import com.rferraz.loans.domain.dto.CustomerLoansOutputDTO;
import com.rferraz.loans.domain.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customer-loans")
public class LoanController {

  private final LoanService loanService;

  public LoanController(LoanService loanService) {
    this.loanService = loanService;
  }

  @PostMapping
  public ResponseEntity<CustomerLoansOutputDTO> insert(@Valid @RequestBody CustomerLoansInputDTO input) {
    return ResponseEntity.status(HttpStatus.OK).body(loanService.getLoans(input));
  }
  
}
