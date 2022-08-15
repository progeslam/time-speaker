package com.smartbear.timespeaker.exception;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceUnprocessableException.class)
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorResponseModel handleNoRecordFoundException(ServiceUnprocessableException ex) {

        return new ErrorResponseModel(ex.getMessage());
    }

}
