package com.hexaware.careercrafterfinal.service;

import com.hexaware.careercrafterfinal.entities.Employer;
import com.hexaware.careercrafterfinal.entities.JobSeeker;

public interface IRegisterationAuthService {

	public boolean registerUser(JobSeeker seeker);
	
	public boolean registerEmployer(Employer employer);
	
	public boolean authenticateUser(String username,String password);
	
	public boolean authenticateEmployer(String username,String password);
}
