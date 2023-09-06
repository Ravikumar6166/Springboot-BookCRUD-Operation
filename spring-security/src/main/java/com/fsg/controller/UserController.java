package com.fsg.controller;

import java.util.List;

import javax.validation.Valid;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsg.constant.Constants;
import com.fsg.constant.RequestMappingConst;
import com.fsg.dto.AuthenticationRequest;
import com.fsg.dto.AuthenticationResponse;
import com.fsg.dto.UserDto;
import com.fsg.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = RequestMappingConst.SIGN_UP, method = RequestMethod.POST, produces = Constants.JSON_FORMAT, consumes = Constants.JSON_FORMAT)
	@ResponseBody
	public ResponseEntity<?> signUp(@Valid @RequestBody UserDto userDto) {
		return ResponseEntity.ok(userService.save(userDto));
	}
	
	/*
    LOGIN
     */
    @RequestMapping(
            value = RequestMappingConst.LOGIN,
            method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    	 AuthenticationResponse authResp =userService.createAuthenticationToken(authenticationRequest, false);
    	return new ResponseEntity<AuthenticationResponse>(authResp, HttpStatus.OK);

       // return ResponseEntity.ok(userService.createAuthenticationToken(authenticationRequest));
    }


	@RequestMapping(value = RequestMappingConst.GET_USERS_DETAILS, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<UserDto>> getCandidateInfo() {

		return new ResponseEntity<List<UserDto>>(userService.findAll(), HttpStatus.OK);

	}
//
//	@RequestMapping(value = RequestMappingConst.GET_USERS_BY_ID, method = RequestMethod.GET)
//	public @ResponseBody ResponseEntity<UserDto> getUserdeatils(@RequestParam("userid") Long userid) {
//		return new ResponseEntity<UserDto>(userService.findOne(userid), HttpStatus.OK);
//
//	}
//	
//	  @RequestMapping(value = RequestMappingConst.SEARCH_USERS_DETAILS, method = RequestMethod.GET,  produces = Constants.JSON_FORMAT)
//		public @ResponseBody ResponseEntity<List<UserDto>> searchCandidates(
//				@RequestParam(value = "type", required = false) String roles,
//				@RequestParam(value = "status", required = false) String status	
//		) {
//		   UserDto searchDto = new UserDto();
//			
//			if (status != null) {
//				searchDto.setStatus(status);
//			}
//			if (roles != null) {
//				searchDto.setRoles(roles);
//			}
//			
//			
//			PageRequest page_req = PageRequest.of(0, 25);
//			List<UserDto> usersDtoList = userService.findUsers(searchDto, page_req);
//			if ((usersDtoList == null))
//				return new ResponseEntity<List<UserDto>>(new ArrayList<UserDto>(), HttpStatus.OK);
//			else
//				return new ResponseEntity<List<UserDto>>(usersDtoList, HttpStatus.OK);
//
//		}
//	   
//	
//	@RequestMapping(value = RequestMappingConst.DELETE_USERS, method = RequestMethod.DELETE)
//	public @ResponseBody ResponseEntity<HttpStatus> delete(@PathVariable(value = "userid") Long userid) {
//	userService.delete(userid);
//	return new ResponseEntity<HttpStatus>(HttpStatus.OK);
//	}
}
