package com.hexaware.careercrafterfinal.service;

import com.hexaware.careercrafterfinal.dto.EmployerDto;
import com.hexaware.careercrafterfinal.dto.JobSeekerDto;
import com.hexaware.careercrafterfinal.entities.Employer;
import com.hexaware.careercrafterfinal.entities.JobSeeker;

public interface IRegisterationAuthService {

	public boolean registerUser(JobSeekerDto seeker);
	
	public boolean registerEmployer(EmployerDto employer);
	
	public boolean authenticateUser(String username,String password);
	
	public boolean authenticateEmployer(String username,String password);
}
