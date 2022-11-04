package com.ku.business.exception;

import java.sql.SQLException;

public class MyOwnException extends SQLException {

    public MyOwnException() {
        super();
    }

    public MyOwnException(String message) {
        super(message);
    }

    public MyOwnException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyOwnException(Throwable cause) {
        super(cause);
    }
}
