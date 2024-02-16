package com.hexaware.careercrafterfinal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.repository.EmployerRepository;
import com.hexaware.careercrafterfinal.repository.JobSeekerRepository;
import com.hexaware.careercrafterfinal.repository.UserInfoRepository;

@Service
public class RegistrationServiceImp implements IRegisterationAuthService {

	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Autowired
	EmployerRepository employerRepository;
	
	@Autowired
	JobSeekerRepository seekerRepository;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	Logger logger =LoggerFactory.getLogger(RegistrationServiceImp.class);
	
	@Override
	public boolean registerUser(UserInfo userInfo) {

		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		logger.info("Registering Job Seeker");
		return userInfoRepository.save(userInfo) != null;
	}

	@Override
	public boolean registerEmployer(UserInfo employerInfo) {

		employerInfo.setPassword(passwordEncoder.encode(employerInfo.getPassword()));
		logger.info("Registering Employer");
		return userInfoRepository.save(employerInfo) != null;
	}
	
}
