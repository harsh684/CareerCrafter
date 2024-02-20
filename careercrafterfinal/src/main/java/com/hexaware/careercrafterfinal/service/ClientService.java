package com.hexaware.careercrafterfinal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.exception.UserAlreadyExistsException;
import com.hexaware.careercrafterfinal.repository.UserInfoRepository;

@Service
public class ClientService {
	@Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
    private static final Logger logger=LoggerFactory.getLogger(ClientService.class); 
	
	public String addUser(UserInfo userInfo) throws UserAlreadyExistsException {
		UserInfo temp = repository.findByEmail(userInfo.getEmail()).orElse(null);
		if(temp!=null ) {
			throw new UserAlreadyExistsException("Account already Exists");
		}
		
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        logger.info("User added to system");
        return "user added to system ";
    }
}
