package com.rferraz.loans.domain.service;

import java.math.BigDecimal;

public class LoanConstants {

  public static boolean isIncomeWithinRange(BigDecimal income) {
    return income.compareTo(MIN_INCOME_TO_GET_LOAN) >= 0 &&
      income.compareTo(MAX_INCOME_GET_LOAN) <= 0;
  }

  public static boolean isValidLocation(String location) {
    return LOCATION_TO_GET_LOAN.equalsIgnoreCase(location);
  }

  public static boolean isUnderAgeLimit(int age) {
    return age < AGE_TO_GET_LOAN;
  }

  public static final BigDecimal MIN_INCOME_TO_GET_LOAN = BigDecimal.valueOf(3000);
  public static final BigDecimal MAX_INCOME_GET_LOAN = BigDecimal.valueOf(5000);
  public static final int AGE_TO_GET_LOAN = 30;
  public static final String LOCATION_TO_GET_LOAN = "SP";
}
