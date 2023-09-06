package com.fsg.exception;

@SuppressWarnings("serial")
public class BaseRuntimeException extends RuntimeException{

    public BaseRuntimeException() {
        super();
    }

    public BaseRuntimeException(String message, Throwable cause,
                                boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

}
