package com.hexaware.careercrafterfinal.service;


import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.exception.AuthenticationException;

public interface IRegisterationAuthService {

	public boolean registerUser(UserInfo userInfo);
	
	public boolean registerEmployer(UserInfo userInfo);
	
}
