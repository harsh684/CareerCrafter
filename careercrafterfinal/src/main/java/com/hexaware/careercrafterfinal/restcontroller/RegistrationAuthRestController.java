package com.hexaware.careercrafterfinal.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.careercrafterfinal.dto.AuthRequest;
import com.hexaware.careercrafterfinal.dto.EmployerDto;
import com.hexaware.careercrafterfinal.dto.JobSeekerDto;
import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.service.ClientService;
import com.hexaware.careercrafterfinal.service.JwtService;

@RestController
@RequestMapping("/api/register")
public class RegistrationAuthRestController {

	Logger logger=LoggerFactory.getLogger(RegistrationAuthRestController.class);
	@Autowired
	JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	ClientService clientService;
	
	@PostMapping("/user")
	public String registerUser(@RequestBody UserInfo userInfo) {
		logger.info("Register user info for user");
		return clientService.addUser(userInfo);
	}

	@PostMapping("/employer")
	public String registerEmployer(@RequestBody UserInfo employerInfo) {
		logger.info("Register employer for userInfo");
		return clientService.addUser(employerInfo);
	}

	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
		
		String token = null;
		
		if(authentication.isAuthenticated()) {
			token = jwtService.generateToken(authRequest.getUsername());
		}else {
			throw new UsernameNotFoundException("Username or Password is invalid");
		}
		return token;
	}
}
