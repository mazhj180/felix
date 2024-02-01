package com.mazhj.common.web.controller;

import com.mazhj.common.core.exception.BusinessException;
import com.mazhj.common.core.exception.SystemException;
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
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = {BusinessException.class})
    public AjaxResult businessExceptionHandler(BusinessException ex){
        log.error(ex.getMessage());
        return AjaxResult.Builder.badRequest();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {SystemException.class})
    public AjaxResult systemExceptionHandler(SystemException ex){
        return AjaxResult.Builder.error();
    }

}
