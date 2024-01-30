package com.foodexpress.accountservice.common.advice;

import com.foodexpress.accountservice.common.advice.exceptions.NotEqualsPassword;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NotEqualsPassword.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<?> notEqualsPassword(NotEqualsPassword notEqualsPassword) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(notEqualsPassword.getMessage());
    }

}
