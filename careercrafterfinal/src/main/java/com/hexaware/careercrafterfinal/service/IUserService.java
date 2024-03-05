package com.hexaware.careercrafterfinal.service;

import java.util.List;

import com.hexaware.careercrafterfinal.dto.JobSeekerDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.JobSeeker;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.exception.ApplicationException;
import com.hexaware.careercrafterfinal.exception.AuthenticationException;
import com.hexaware.careercrafterfinal.exception.ListingNotFoundException;
import com.hexaware.careercrafterfinal.exception.ProfileNotFoundException;
import com.hexaware.careercrafterfinal.exception.ProfileUpdateException;
import com.hexaware.careercrafterfinal.exception.UserAlreadyExistsException;

public interface IUserService {

	public boolean createProfile(JobSeeker seeker) throws ProfileUpdateException, UserAlreadyExistsException;

	public boolean updateProfile(JobSeeker seeker) throws ProfileUpdateException;
	
	public List<Listing> searchJobs();
	
	public boolean applyForJob(long listingId, Applications application) throws ApplicationException, ListingNotFoundException, ProfileNotFoundException ;
	
	public List<Applications> getAppliedJobs();
	
	public String trackStatus(long applicationId) throws ApplicationException ;

	public JobSeeker getUserProfile();
	
	public boolean editResume(Resume resume);

	public Listing getListingByApplicationId(long applicationId);

	public Resume getCrafterResume() throws AuthenticationException;

//	public String getSeekerNameByResumeId(long resumeId);
}
