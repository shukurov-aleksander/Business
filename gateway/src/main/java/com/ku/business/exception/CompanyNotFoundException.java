package com.ku.business.exception;
public class CompanyNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return super.getMessage() + "EXCEPTIOOOOOOOOOOOOOOOOOOOON!";
    }
}
