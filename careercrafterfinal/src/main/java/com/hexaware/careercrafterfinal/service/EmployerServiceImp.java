package com.hexaware.careercrafterfinal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hexaware.careercrafterfinal.config.UserDetailsImp;
import com.hexaware.careercrafterfinal.dto.EmployerDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Employer;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.repository.ApplicationRepository;
import com.hexaware.careercrafterfinal.repository.EmployerRepository;
import com.hexaware.careercrafterfinal.repository.ListingRepository;
import com.hexaware.careercrafterfinal.repository.ResumeRepository;
import com.hexaware.careercrafterfinal.repository.UserInfoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployerServiceImp implements IEmployerService {

	@Autowired
	EmployerRepository employerRepo;

	@Autowired
	ApplicationRepository applicationRepo;

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	ListingRepository listingRepository;

	@Autowired
	ResumeRepository resumeRepository;

	private static final Logger logger = LoggerFactory.getLogger(EmployerServiceImp.class);

	@Override
	public boolean createProfile(EmployerDto emp) {

		Employer employer = new Employer();
		employer.setName(emp.getName());
		employer.setAddress(emp.getAddress());
		employer.setEmail(emp.getEmail());
		employer.setToken(emp.getToken());
		employer.setCompanyName(emp.getCompanyName());
		employer.setPhno(emp.getPhno());
		employer.setListings(emp.getListings());
		employer.setemployerId(emp.getemployerId());

		Employer temp = employerRepo.save(employer);

		if (temp == null)
			return false;

		UserInfo userInfo;
		try {
			userInfo = getCurrentUserInfo();
			if (userInfo.getRole().equalsIgnoreCase("Employer")) {
				userInfo.setRoleId(temp.getemployerId());
				userInfoRepository.save(userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean updateProfile(EmployerDto emp) {
		logger.info("Updating profile for employer: {}", emp.getemployerId());
		Employer employer = new Employer();
		employer.setName(emp.getName());
		employer.setAddress(emp.getAddress());
		employer.setEmail(emp.getEmail());
		employer.setToken(emp.getToken());
		employer.setCompanyName(emp.getCompanyName());
		employer.setPhno(emp.getPhno());
		employer.setListings(emp.getListings());
		employer.setemployerId(emp.getemployerId());
		UserInfo userInfo;
		try {
			userInfo = getCurrentUserInfo();
			if (userInfo.getRole().equalsIgnoreCase("Employer")) {
				employer.setemployerId(userInfo.getRoleId());
			}
		} catch (Exception e) {
	        logger.error("Error occurred while retrieving current user info.", e);
			e.printStackTrace();
		}

		if (employerRepo.save(employer) == null) {
			logger.info("Failed to update profile for enokoyer:{}",emp.getemployerId());
			return false;
		}
		logger.info("Profile updated successfully for employer: {}",emp.getemployerId());
		return true;
	}

	@Override
	public boolean postListing(Listing listing) {
		logger.info("Posting listing with ID: {}", listing.getJd());
		UserInfo userInfo;
		try {
			userInfo = getCurrentUserInfo();
			if (userInfo.getRole().equalsIgnoreCase("Employer")) {
				listing.setEmployer(employerRepo.findById(userInfo.getRoleId()).orElse(null));
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return listingRepository.save(listing) != null;
	}

	@Override
	public boolean updateListing(long listingId, Listing listing) {
		  logger.info("Updating listing with ID: {}", listingId);
		    
		    // Save the updated listing and return true if successful, false otherwise
		    boolean updated = listingRepository.save(listing) != null;
		    if (updated) {
		        logger.info("Listing updated successfully with ID: {}", listingId);
		    } else {
		        logger.error("Failed to update listing with ID: {}", listingId);
		    }
		    return updated;
	}

	@Override
	public boolean deleteListing(long listingId) {
	    logger.info("Deleting listing with ID: {}", listingId);

		listingRepository.deleteById(listingId);
		return true;
	}

	@Override
	public List<Applications> viewApplications() {
	    logger.info("Fetching all applications");

		return applicationRepo.findAll();
	}

	@Override
	public boolean changeApplicationStatus(long applicationId, String status) {
	    logger.info("Changing status of application with ID: {} to {}", applicationId, status);

		return applicationRepo.updateStatus(status, applicationId) > 0;
	}

	@Override
	public List<Resume> manageResumeDb() {
	    logger.info("Fetching all resumes");

		return resumeRepository.findAll();
	}

	private UserInfo getCurrentUserInfo() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!authentication.isAuthenticated()) {
			logger.info("Authentication is not authenticated");
			throw new Exception();
		}
		UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
		logger.info("Retrieving userDetail");
		return userInfoRepository.findByName(userDetailsImp.getUsername()).orElse(null);
	}
}
