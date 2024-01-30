package com.foodexpress.accountservice.common.advice.exceptions;

public class NotEqualsPassword extends RuntimeException {

    public NotEqualsPassword(String message) {
        super(message);
    }

}
