package com.hexaware.careercrafterfinal.service;

import java.util.List;

import com.hexaware.careercrafterfinal.dto.EmployerDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.exception.AccountNotCreatedException;
import com.hexaware.careercrafterfinal.exception.ApplicationException;
import com.hexaware.careercrafterfinal.exception.ListingNotCreatedException;
import com.hexaware.careercrafterfinal.exception.ListingNotUpdatedException;


public interface IEmployerService {

	public boolean createProfile(EmployerDto employer) throws AccountNotCreatedException;
	
	public boolean updateProfile(EmployerDto employer) throws AccountNotCreatedException;

	public boolean postListing(Listing listing) throws ListingNotCreatedException;
	
	public boolean updateListing(long listingId, Listing listing) throws ListingNotUpdatedException;

	public boolean deleteListing(long listingId) throws ListingNotUpdatedException;
	
	public List<Applications> viewApplications();
	
	public boolean changeApplicationStatus(long applicationId, String status) throws ApplicationException;
	
	public List<Resume> manageResumeDb();
}
