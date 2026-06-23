package com.example.departmentservice.exception;

import org.springframework.http.HttpStatus;

public class CustomBadRequestException extends BusinessException {

  public CustomBadRequestException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }
}
