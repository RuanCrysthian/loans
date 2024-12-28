package com.rferraz.loans.infra.controller.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ValidationError err = new ValidationError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Validation exception");
    err.setPath(request.getRequestURI());
    StringBuilder messageBuilder = new StringBuilder();
    for (FieldError f : e.getBindingResult().getFieldErrors()) {
      err.addError(f.getField(), f.getDefaultMessage());
      messageBuilder.append(f.getDefaultMessage()).append("; ");
    }
    err.setMessage(messageBuilder.toString().trim());
    return ResponseEntity.status(status).body(err);
  }
}
