package com.example.employeeservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Object message
      , HttpServletRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
        LocalDateTime.now(),
        httpStatus.value(),
        httpStatus.getReasonPhrase(),
        message,
        request.getRequestURI()
    );

    return ResponseEntity.status(httpStatus).body(errorResponse);
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
    return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
  }

  @ExceptionHandler(value = ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
      HttpServletRequest request) {
    return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage(), request);
  }

  @ExceptionHandler(value = CustomBadRequestException.class)
  public ResponseEntity<ErrorResponse> handleCustomBadRequestException(CustomBadRequestException ex,
      HttpServletRequest request) {
    return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
  }
}
