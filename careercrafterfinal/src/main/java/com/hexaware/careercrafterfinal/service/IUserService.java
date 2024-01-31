package com.hexaware.careercrafterfinal.service;

import java.util.List;

import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.JobSeeker;
import com.hexaware.careercrafterfinal.entities.Listing;

public interface IUserService {

public boolean createProfile();

	public boolean createProfile(JobSeeker seeker);

	public boolean updateProfile(JobSeeker seeker);
	
	public List<Listing> searchJobs();
	
	public boolean applyForJob(Listing listing);
	
	public List<Applications> getAppliedJobs(JobSeeker seeker);
	
	public String trackStatus(JobSeeker seeker);
}
