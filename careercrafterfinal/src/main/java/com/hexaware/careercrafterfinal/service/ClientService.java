package com.hexaware.careercrafterfinal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.careercrafterfinal.config.UserDetailsImp;
import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.exception.AuthenticationException;
import com.hexaware.careercrafterfinal.exception.UserAlreadyExistsException;
import com.hexaware.careercrafterfinal.repository.UserInfoRepository;

@Service
public class ClientService implements IClientService {
	
	@Autowired
    private UserInfoRepository userInfoRepository;

	@Autowired
	EmailService emailService;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
    private static final Logger logger=LoggerFactory.getLogger(ClientService.class); 
	
    @Override
	public String addUser(UserInfo userInfo) throws Exception {
		UserInfo temp = userInfoRepository.findByEmail(userInfo.getEmail()).orElse(null);
		if(temp!=null ) {
			throw new UserAlreadyExistsException("Account already Exists");
		}
		
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        boolean isTrue = userInfoRepository.save(userInfo)!=null;
        logger.info("User added to system");
        
        if(isTrue) {
        	emailService.sendRegistrationMail(userInfo.getEmail());
        }
        
        return "user added to system ";
    }
	
	@Override
	public UserInfo getCurrentUserInfo() throws AuthenticationException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.isAuthenticated()) {
			logger.info("Could not authenticated");

			throw new AuthenticationException();
		}
		UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
		UserInfo currentUser = userInfoRepository.findByName(userDetailsImp.getUsername()).orElse(null);
		currentUser.setPassword(null);
		return currentUser;
	}
}
