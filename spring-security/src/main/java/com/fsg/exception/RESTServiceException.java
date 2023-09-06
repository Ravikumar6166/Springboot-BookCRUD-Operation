package com.fsg.exception;

@SuppressWarnings("serial")
public class RESTServiceException extends BaseRuntimeException {

	public RESTServiceException() {
		super();
	}

	public RESTServiceException(String message, Throwable cause,
                                boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RESTServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public RESTServiceException(String message) {
		super(message);
	}

	public RESTServiceException(Throwable cause) {
		super(cause);
	}

}
