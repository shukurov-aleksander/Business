//package com.ku.business.exception;
//
//import lombok.Data;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class ExceptionHandlers extends ResponseEntityExceptionHandler{
//
//    @ExceptionHandler(CompanyNotFoundException.class)
//    @ResponseBody
//    public ErrorResponse handleUserNotFoundException(final CompanyNotFoundException ex) {
//        return new ErrorResponse("COMPANY_NOT_FOUND", "The company was not found");
//    }
//
//    @ExceptionHandler(CompanyNotFoundException.InternalServerError.class)
//    @ResponseBody
//    public ErrorResponse handleThrowable(final CompanyNotFoundException.InternalServerError ex) {
//        return new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected internal server error occured");
//    }
//
//    @Data
//    public static class ErrorResponse {
//        private final String code;
//        private final String message;
//    }
//}
