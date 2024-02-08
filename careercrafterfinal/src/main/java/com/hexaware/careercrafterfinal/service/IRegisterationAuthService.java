package com.hexaware.careercrafterfinal.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.hexaware.careercrafterfinal.dto.AuthRequest;
import com.hexaware.careercrafterfinal.entities.UserInfo;

public interface IRegisterationAuthService {

	public boolean registerUser(UserInfo userInfo);
	
	public boolean registerEmployer(UserInfo userInfo);
}
