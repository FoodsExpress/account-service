package com.foodexpress.accountservice.common.advice.exceptions;

public class NotMatchedPasswordException extends RuntimeException {

    public NotMatchedPasswordException(String message) {
        super(message);
    }

}
