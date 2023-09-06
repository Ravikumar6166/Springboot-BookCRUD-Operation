package com.fsg.exception;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends RESTServiceException{

	@Override
	public String getMessage() {
		return "User with given username already exists";
	}

	
}
