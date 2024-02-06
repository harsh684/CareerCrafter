package com.hexaware.careercrafterfinal.service;

import java.util.List;

import com.hexaware.careercrafterfinal.dto.EmployerDto;
import com.hexaware.careercrafterfinal.dto.ListingDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Resume;


public interface IEmployerService {

	public boolean createProfile(EmployerDto employer);
	
	public boolean updateProfile(EmployerDto employer);

	public boolean postListing(Listing listing);
	
	public boolean updateListing(long listingId, Listing listing);

	public boolean deleteListing(long listingId);
	
	public List<Applications> viewApplications();
	
	public boolean changeApplicationStatus(long applicationId, String status);
	
	public List<Resume> manageResumeDb();
}
