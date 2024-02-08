package com.hexaware.careercrafterfinal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hexaware.careercrafterfinal.config.UserDetailsImp;
import com.hexaware.careercrafterfinal.dto.JobSeekerDto;
import com.hexaware.careercrafterfinal.dto.ListingDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.JobSeeker;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.repository.JobSeekerRepository;
import com.hexaware.careercrafterfinal.repository.ListingRepository;
import com.hexaware.careercrafterfinal.repository.UserInfoRepository;


@Service
public class UserServiceImp implements IUserService {

	@Autowired
	JobSeekerRepository seekerRepository;
	
	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Autowired
	ListingRepository listingRepository;
	
	Logger logger =LoggerFactory.getLogger(UserServiceImp.class);
	
	@Override
	public boolean createProfile(JobSeekerDto seeker) {
		logger.info("Jobseeker profile creating for: {}",seeker.getName());
		JobSeeker jobSeeker = new JobSeeker();
		jobSeeker.setName(seeker.getName());
		jobSeeker.setEmail(seeker.getEmail());
		jobSeeker.setAddress(seeker.getAddress());
		jobSeeker.setCountry(seeker.getCountry());
		jobSeeker.setPhno(seeker.getPhno());
		jobSeeker.setCtc(seeker.getCtc());
		jobSeeker.setDateOfBirth(seeker.getDateOfBirth());
		jobSeeker.setApplications(seeker.getApplications());
		jobSeeker.setResume(seeker.getResume());
		jobSeeker.setSummary(seeker.getSummary());
		jobSeeker.setTagline(seeker.getTagline());
		
		JobSeeker temp = seekerRepository.save(jobSeeker);
		
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase("Seeker")) {
				currentUser.setRoleId(temp.getId());
				userInfoRepository.save(currentUser);
			}
		} catch (Exception e) {
	        logger.error("Error occurred while creating profile for job seeker", e);

			
			e.printStackTrace();
		}
		
		return temp!=null;
	}

	@Override
	public boolean updateProfile(JobSeeker seeker) {
	    logger.info("Updating profile for job seeker with ID: {}", seeker.getId());

		return seekerRepository.save(seeker) != null;
	}

	@Override
	public List<Listing> searchJobs() {
	    logger.info("Searching for jobs");

		return listingRepository.findAll();
	}

	@Override
	public boolean applyForJob(long listingId, Applications application) {
		
		return false;
	}

	@Override
	public List<Applications> getAppliedJobs() {
	    logger.info("Fetching applied jobs for current job seeker");

		JobSeeker seeker;
		
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase("Seeker")) {
				seeker  = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
				return seeker.getApplications();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String trackStatus(long applicationId) {
	    logger.info("Tracking status for application with ID: {}", applicationId);

		JobSeeker seeker;
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase("Seeker")) {
				seeker  = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
				//change logic
				return seeker.getApplications().get(1).getStatus();
			}
		} catch (Exception e) {
	        logger.error("Error occurred while tracking status for application", e);

			e.printStackTrace();
		}
		return null;
	}

	private UserInfo getCurrentUserInfo() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.isAuthenticated()) {
			
			throw new Exception();
		}
		UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
		return userInfoRepository.findByName(userDetailsImp.getUsername()).orElse(null);
	}

}
