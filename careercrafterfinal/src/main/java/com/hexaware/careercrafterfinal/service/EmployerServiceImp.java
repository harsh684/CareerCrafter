package com.hexaware.careercrafterfinal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.careercrafterfinal.dto.EmployerDto;
import com.hexaware.careercrafterfinal.dto.ListingDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Resume;

@Service
public class EmployerServiceImp implements IEmployerService {

	@Override
	public boolean createProfile(EmployerDto employer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProfile(EmployerDto employer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postListing(ListingDto listing) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateListing(ListingDto listing) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteListing(long listingId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Applications> viewApplications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeApplicationStatus(long applicationId, String status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Resume> manageResumeDb() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
