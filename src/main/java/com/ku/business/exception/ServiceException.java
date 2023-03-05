package com.ku.business.exception;

public class ServiceException extends RuntimeException{

    private ServiceException() {
    }

    public static NotFoundException notFoundException(String message, Throwable cause){
        return new NotFoundException(message, cause);
    }
}
