package com.foodexpress.accountservice.common.advice.exceptions;

public class NotValidAccountException extends RuntimeException {

    public NotValidAccountException(String message) {
        super(message);
    }

}
