package com.foodexpress.accountservice.common.advice;

import com.foodexpress.accountservice.common.advice.exceptions.AlreadyPresentAccountException;
import com.foodexpress.accountservice.common.advice.exceptions.NotMatchedPasswordException;
import com.foodexpress.accountservice.common.advice.exceptions.NotValidAccountException;
import com.foodexpress.accountservice.common.advice.exceptions.NotValidJwtTokenException;
import com.foodexpress.accountservice.common.util.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.foodexpress.accountservice.common.util.ApiUtil.fail;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    protected ApiUtil.ApiResult<Void> defaultException(Exception e) {
        e.printStackTrace();
        return fail(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
        NotValidAccountException.class,
        NotMatchedPasswordException.class,
        AlreadyPresentAccountException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiUtil.ApiResult<Void> notValidAccountException(Exception e) {
        e.printStackTrace();
        return fail(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
        NotValidJwtTokenException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ApiUtil.ApiResult<Void> unauthorizedException(Exception e) {
        e.printStackTrace();
        return fail(e, HttpStatus.UNAUTHORIZED);
    }

}
