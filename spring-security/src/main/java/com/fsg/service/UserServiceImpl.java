package com.fsg.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fsg.dto.AuthenticationRequest;
import com.fsg.dto.AuthenticationResponse;
import com.fsg.dto.UserDto;
import com.fsg.exception.UserAlreadyExistsException;
import com.fsg.model.User;
import com.fsg.repo.UserRepo;
import com.fsg.security.PasswordHash;
import com.fsg.utils.JwtUtil;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
//	@Autowired
//	UsersCustomRepo usersCustomRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
//	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	
	
	@Override
	public UserDto findByEmail(String email) {
		return modelMapper.map(userRepository.findByEmail(email).get(), UserDto.class);
	}

	@Override
	public UserDto save(UserDto userDto) {
		if(userDto.getId()==null) {
			if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
				throw new UserAlreadyExistsException();
			}
			
			userDto.setPassword(PasswordHash.createHash(userDto.getPassword()));
			}
			User user = modelMapper.map(userDto, User.class);
//	        user.setRoles("ROLE_USER");
			user.setActive(true);
			return modelMapper.map(userRepository.save(user), UserDto.class);
	}

	@Override
	public List<UserDto> findAll() {
		List<User> UsersList = userRepository.findAll();
		if (UsersList != null) {
			List<UserDto> usersDtoList = new ArrayList<UserDto>();
			for (User users : UsersList) {
				UserDto usersDto = modelMapper.map(users, UserDto.class);
				
				
				usersDtoList.add(usersDto);
			}
			Collections.reverse(usersDtoList);
			return usersDtoList;

		} else
			return null;
	}
//
//	@Override
//	public void delete(Long userid) {
//		 User user = userRepository.findById(userid).get(); 
//		 if(user!=null) {
//			 
//			 user.setStatus("IN-ACTIVE");
//			 user.setActive(false);
//			 
//		 }
//		 //userRepository.deleteById(userid);
//		 userRepository.save(user);
//		
//	}
//
//	
//
//	@Override
//	public UserDto findOne(Long userid) {
//		User user = userRepository.findById(userid).get();
//		UserDto usersDto = modelMapper.map(user, UserDto.class);
//        return usersDto;
//	}
//
//	@Override
//	public List<UserDto> findUsers(UserDto searchDto, Pageable page) {
//		List<User> users = usersCustomRepo.findUsers(searchDto, page);
//		List<UserDto> userDtoList = new ArrayList<UserDto>();
//		for (User user : users) {
//			UserDto usersDto = modelMapper.map(user, UserDto.class);
////	    			candidatesHelper.buildDto(candidate);
//
//			userDtoList.add(usersDto);
//		}
//		return userDtoList;
//	}
//
	@Override
	public AuthenticationResponse createAuthenticationToken(AuthenticationRequest authenticationRequest,
			boolean isAdmin) throws Exception {
		Optional<User> user = userRepository.findByEmail(authenticationRequest.getUsername());
		boolean password = PasswordHash.validatePassword(authenticationRequest.getPassword(), user.get().getPassword());
		if (!password) {
			throw new Exception("Incorrect credentials");
		}
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), user.get().getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect credentials", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		UserDto userDto = modelMapper.map(user.get(), UserDto.class);
		return new AuthenticationResponse(jwt, userDto);
	}

	

}
