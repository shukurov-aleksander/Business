package com.ku.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Company not found")
public class CompanyNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return super.getMessage() + "EXCEPTIOOOOOOOOOOOOOOOOOOOON!";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error rdsfsd")
    public class InternalServerError extends RuntimeException {
        @Override
        public String getMessage() {
            return super.getMessage() + "EXCEPTIOOOOOOOOOOOOOOOOOOOON!";
        }
    }
}
