package com.inhatc.noticebot.common.exception;

import com.inhatc.noticebot.common.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DomainException.class)
  public ResponseEntity<ErrorResponse> handleDomainException(DomainException ex) {
    ErrorCode errorCode = ex.getErrorCode();
    return toResponse(errorCode, ex.getDetails());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex) {
    Map<String, List<String>> details = extractFieldErrors(ex.getBindingResult());
    return toResponse(ErrorCode.VALIDATION_FAILED, details);
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<ErrorResponse> handleBindException(BindException ex) {
    Map<String, List<String>> details = extractFieldErrors(ex.getBindingResult());
    return toResponse(ErrorCode.VALIDATION_FAILED, details);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
    Map<String, List<String>> details = new LinkedHashMap<>();
    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      String field = violation.getPropertyPath().toString();
      details.computeIfAbsent(field, key -> new ArrayList<>()).add(violation.getMessage());
    }
    return toResponse(ErrorCode.VALIDATION_FAILED, details);
  }

  @ExceptionHandler({IllegalArgumentException.class, HttpMessageNotReadableException.class})
  public ResponseEntity<ErrorResponse> handleBadRequest(Exception ignored) {
    return toResponse(ErrorCode.INVALID_REQUEST, null);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnhandled(Exception ignored) {
    return toResponse(ErrorCode.INTERNAL_ERROR, null);
  }

  private ResponseEntity<ErrorResponse> toResponse(ErrorCode errorCode, Object details) {
    ErrorResponse response = ErrorResponse.of(errorCode.getCode(), errorCode.getMessage(), details);
    return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
  }

  private Map<String, List<String>> extractFieldErrors(BindingResult bindingResult) {
    Map<String, List<String>> details = new LinkedHashMap<>();
    for (FieldError fieldError : bindingResult.getFieldErrors()) {
      details
          .computeIfAbsent(fieldError.getField(), key -> new ArrayList<>())
          .add(fieldError.getDefaultMessage());
    }
    return details;
  }
}
