package com.hexaware.careercrafterfinal.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hexaware.careercrafterfinal.config.UserDetailsImp;
import com.hexaware.careercrafterfinal.dto.EmployerDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Employer;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.exception.AccountNotCreatedException;
import com.hexaware.careercrafterfinal.exception.ApplicationException;
import com.hexaware.careercrafterfinal.exception.AuthenticationException;
import com.hexaware.careercrafterfinal.exception.ListingNotCreatedException;
import com.hexaware.careercrafterfinal.exception.ListingNotUpdatedException;
import com.hexaware.careercrafterfinal.exception.UserAlreadyExistsException;


public interface IEmployerService {

	public boolean createProfile(EmployerDto employer) throws AccountNotCreatedException, UserAlreadyExistsException;
	
	public boolean updateProfile(EmployerDto employer) throws AccountNotCreatedException;

	public boolean postListing(Listing listing) throws ListingNotCreatedException;
	
	public boolean updateListing(long listingId, Listing listing) throws ListingNotUpdatedException;

	public boolean changeListingStatus(long listingId,String status) throws ListingNotUpdatedException;
	
	public List<Applications> viewApplications();
	
	public List<Applications> viewApplicationsForListing(long listingId);
	
	public boolean changeApplicationStatus(long applicationId, String status) throws ApplicationException;
	
	public List<Resume> manageResumeDb();

	public Employer getProfile();

//	public List<Listing> getAllListings();

	public List<Listing> getEmployerListings();

	public Resume getResumeById(long resumeId);
	
}
