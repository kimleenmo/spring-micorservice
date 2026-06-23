package com.example.departmentservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private ResponseEntity<ErrorResponse> buildErrorResponse(Object message, HttpStatus status,
      HttpServletRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
        LocalDateTime.now(),
        status.value(),
        status.getReasonPhrase(),
        message,
        request.getServletPath()
    );

    return ResponseEntity.status(status).body(errorResponse);
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
    return buildErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @ExceptionHandler(value = ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e,
      HttpServletRequest request) {
    return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = CustomBadRequestException.class)
  public ResponseEntity<ErrorResponse> handleCustomBadRequestException(CustomBadRequestException e,
      HttpServletRequest request) {
    return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, request);
  }

}
