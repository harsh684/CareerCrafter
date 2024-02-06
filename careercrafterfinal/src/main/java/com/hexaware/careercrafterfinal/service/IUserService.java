package com.hexaware.careercrafterfinal.service;

import java.util.List;

import com.hexaware.careercrafterfinal.dto.JobSeekerDto;
import com.hexaware.careercrafterfinal.dto.ListingDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.JobSeeker;
import com.hexaware.careercrafterfinal.entities.Listing;

public interface IUserService {

	public boolean createProfile(JobSeekerDto seeker);

	public boolean updateProfile(JobSeeker seeker);
	
	public List<Listing> searchJobs();
	
	public boolean applyForJob(long listingId, Applications application);
	
	public List<Applications> getAppliedJobs();
	
	public String trackStatus(long applicationId);
}
