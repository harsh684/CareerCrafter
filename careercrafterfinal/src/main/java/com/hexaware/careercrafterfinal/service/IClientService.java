package com.hexaware.careercrafterfinal.service;

import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.exception.AuthenticationException;
import com.hexaware.careercrafterfinal.exception.UserAlreadyExistsException;

public interface IClientService {

	public String addUser(UserInfo userInfo) throws UserAlreadyExistsException, Exception;
	
	public UserInfo getCurrentUserInfo() throws AuthenticationException;
}
