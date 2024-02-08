package com.foodexpress.accountservice.common.advice;

import com.foodexpress.accountservice.common.advice.exceptions.NotValidAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NotValidAccountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<?> notValidAccountException(NotValidAccountException notValidAccountException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(notValidAccountException.getMessage());
    }

}
