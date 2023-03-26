package com.ku.business.controller;

import com.ku.business.exception.CompanyNotFoundException;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CompanyNotFoundException.class)
    protected ManualExceptionBody handleNotFoundException(CompanyNotFoundException ex) {
        return new ManualExceptionBody().setHttpStatus(HttpStatus.NOT_FOUND).setMessage(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    protected ManualExceptionBody handleRuntimeException() {
        String bodyOfResponse = "501 Internal service error";
        return new ManualExceptionBody().setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR).setMessage(bodyOfResponse);
    }
    @Data
    @Accessors(chain = true)
    public static class ManualExceptionBody {
        private HttpStatus httpStatus;
        private String message;
    }
}