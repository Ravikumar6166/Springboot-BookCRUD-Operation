package com.fsg.service;

import java.util.List;

import com.fsg.dto.AuthenticationRequest;
import com.fsg.dto.AuthenticationResponse;
import com.fsg.dto.UserDto;


//public interface UserService extends UserDetailsService {
public interface UserService {
    UserDto findByEmail(String email);
    UserDto save(UserDto registration);
    //UserDto updatePassword(UserDto userDto);
      AuthenticationResponse createAuthenticationToken(AuthenticationRequest authenticationRequest, boolean isAdmin) throws Exception;
      public List<UserDto> findAll();
//    public void delete(Long userid);
//    public List<UserDto> findUsers(UserDto searchDto,Pageable page);
//    public UserDto findOne(Long userid);
	
}
