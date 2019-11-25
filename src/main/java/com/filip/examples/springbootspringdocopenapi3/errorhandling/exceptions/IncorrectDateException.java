package com.filip.examples.springbootspringdocopenapi3.errorhandling.exceptions;

public class IncorrectDateException extends RuntimeException {

    public IncorrectDateException() {
        super();
    }

    public IncorrectDateException(String message) {
        super(message);
    }

    public IncorrectDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDateException(Throwable cause) {
        super(cause);
    }

    protected IncorrectDateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
