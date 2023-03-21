package com.ku.business.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlers {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CompanyNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleUserNotFoundException(final CompanyNotFoundException ex) {
        return new ErrorResponse("USER_NOT_FOUND", "The user was not found");
    }

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Throwable.class)
//    @ResponseBody
//    public ErrorResponse handleThrowable(final Throwable ex) {
//        return new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected internal server error occured");
//    }

    @Data
    public static class ErrorResponse {
        private final String code;
        private final String message;
    }
}
