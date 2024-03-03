package com.hexaware.careercrafterfinal.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.careercrafterfinal.dto.AuthRequest;
import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.exception.AuthenticationException;
import com.hexaware.careercrafterfinal.service.ClientService;
import com.hexaware.careercrafterfinal.service.IClientService;
import com.hexaware.careercrafterfinal.service.JwtService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/register")
public class RegistrationAuthRestController {

	@Autowired
	JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	IClientService clientService;
	
	Logger logger=LoggerFactory.getLogger(RegistrationAuthRestController.class);

	
	@PostMapping("/user")
	public String registerUser(@RequestBody UserInfo userInfo) throws Exception {
		logger.info("Hitting API to register user info for job seeker");
		userInfo.setRole("SEEKER");
		return clientService.addUser(userInfo);
	}

	@PostMapping("/employer")
	public String registerEmployer(@RequestBody UserInfo employerInfo) throws Exception {
		logger.info("Hitting API to register user info for employr");
		employerInfo.setRole("EMPLOYER");
		return clientService.addUser(employerInfo);
	}

	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
		
		String token = null;
		
		if(authentication.isAuthenticated()) {
			logger.info("Generating jwt auth token");
			token = jwtService.generateToken(authRequest.getUsername());
		}else {
			throw new UsernameNotFoundException("Username or Password is invalid");
		}
		logger.info("Returning jwt token "+token);
		return token;
	}
	
	@GetMapping("/getCurrentUser")
	public UserInfo getCurrentUserInfo() throws AuthenticationException {
		logger.info("Sending current user Info");
		return clientService.getCurrentUserInfo();
	}
	
}
