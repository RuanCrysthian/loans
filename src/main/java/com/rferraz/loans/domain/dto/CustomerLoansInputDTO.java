package com.rferraz.loans.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

public record CustomerLoansInputDTO(
  @PositiveOrZero(message = "Age must be positive or zero")
  @NotNull(message = "Age is required")
  Integer age,

  @NotBlank(message = "CPF is required")
  @CPF(message = "Invalid CPF format")
  String cpf,

  @NotBlank(message = "Name is required")
  String name,

  @PositiveOrZero(message = "Income must be positive or zero")
  @NotNull(message = "Income is required")
  BigDecimal income,

  @NotBlank(message = "Location is required")
  String location
) {
}
