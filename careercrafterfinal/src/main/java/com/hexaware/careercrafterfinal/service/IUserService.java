package com.hexaware.careercrafterfinal.service;

import java.util.List;

import com.hexaware.careercrafterfinal.dto.JobSeekerDto;
import com.hexaware.careercrafterfinal.dto.ListingDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Listing;

public interface IUserService {

public boolean createProfile();

	public boolean createProfile(JobSeekerDto seeker);

	public boolean updateProfile(JobSeekerDto seeker);
	
	public List<Listing> searchJobs();
	
	public boolean applyForJob(ListingDto listing);
	
	public List<Applications> getAppliedJobs(JobSeekerDto seeker);
	
	public String trackStatus(JobSeekerDto seeker);
}
