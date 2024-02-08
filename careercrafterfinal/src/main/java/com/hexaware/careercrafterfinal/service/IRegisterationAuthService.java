package com.hexaware.careercrafterfinal.service;


import com.hexaware.careercrafterfinal.entities.UserInfo;

public interface IRegisterationAuthService {

	public boolean registerUser(UserInfo userInfo);
	
	public boolean registerEmployer(UserInfo userInfo);
}
