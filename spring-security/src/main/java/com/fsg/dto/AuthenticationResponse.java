package com.fsg.dto;

public class AuthenticationResponse {

    private final String jwt;
    private String loginStatus;
    private UserDto userDto;

    public AuthenticationResponse(String jwt, UserDto userDto) {
        this.jwt = jwt;
        this.userDto = userDto;
    }

    public String getJwt() {
        return jwt;
    }

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
    
}
