package com.hexaware.careercrafterfinal.service;

import java.util.List;

import com.hexaware.careercrafterfinal.dto.JobSeekerDto;
import com.hexaware.careercrafterfinal.dto.ListingDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Listing;

public class UserServiceImp implements IUserService {

	@Override
	public boolean createProfile() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createProfile(JobSeekerDto seeker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProfile(JobSeekerDto seeker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Listing> searchJobs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean applyForJob(ListingDto listing) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Applications> getAppliedJobs(JobSeekerDto seeker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String trackStatus(JobSeekerDto seeker) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
