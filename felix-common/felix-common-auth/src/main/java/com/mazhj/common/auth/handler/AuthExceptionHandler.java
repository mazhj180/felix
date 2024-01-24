package com.mazhj.common.auth.handler;

import com.mazhj.common.core.exception.AuthException;
import com.mazhj.common.web.response.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author mazhj
 */
@Slf4j
@RestControllerAdvice
public class AuthExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {AuthException.class})
    public AjaxResult authExceptionHandler(AuthException ex){
        return AjaxResult.Builder.unAuthorized();
    }

}
