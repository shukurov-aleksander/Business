package com.ku.business.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.UncategorizedSQLException;

@Getter
@Setter
public class CompanyNotFoundException extends RuntimeException {
    private HttpStatus httpStatus;

    public CompanyNotFoundException(String message, UncategorizedSQLException exception, HttpStatus httpStatus) {
        super(message, exception);
        this.httpStatus = httpStatus;
    }
}
