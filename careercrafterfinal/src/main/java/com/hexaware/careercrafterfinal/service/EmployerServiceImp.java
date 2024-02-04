package com.hexaware.careercrafterfinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.careercrafterfinal.dto.EmployerDto;
import com.hexaware.careercrafterfinal.dto.ListingDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Employer;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.repository.EmployerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployerServiceImp implements IEmployerService {

	@Autowired
	EmployerRepository repo;
	
	@Override
	public boolean createProfile(EmployerDto emp) {
		// TODO Auto-generated method stub
		Employer employer = new Employer();
		employer.setName(emp.getName());
		employer.setAddress(emp.getAddress());
		employer.setEmail(emp.getEmail());
		employer.setToken(emp.getToken());
		employer.setComppanyName(emp.getComppanyName());
		employer.setPhno(emp.getPhno());
		employer.setListings(emp.getListings());
		employer.setemployerId(emp.getemployerId());
		
		if(repo.save(employer)==null)
			return false;
		
		return true;
	}

	@Override
	public boolean updateProfile(EmployerDto emp) {
		Employer employer = new Employer();
		employer.setName(emp.getName());
		employer.setAddress(emp.getAddress());
		employer.setEmail(emp.getEmail());
		employer.setToken(emp.getToken());
		employer.setComppanyName(emp.getComppanyName());
		employer.setPhno(emp.getPhno());
		employer.setListings(emp.getListings());
		employer.setemployerId(emp.getemployerId());
		
		if(repo.save(employer)==null)
			return false;
		
		return true;
	}

	@Override
	public boolean postListing(ListingDto listing) {
		
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
