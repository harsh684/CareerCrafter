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
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.exception.ListingNotFoundException;
import com.hexaware.careercrafterfinal.repository.JobSeekerRepository;
import com.hexaware.careercrafterfinal.repository.ListingRepository;
import com.hexaware.careercrafterfinal.repository.ResumeRepository;
import com.hexaware.careercrafterfinal.repository.UserInfoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImp implements IUserService {

	@Autowired
	JobSeekerRepository seekerRepository;
	
	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Autowired
	ListingRepository listingRepository;
	
	@Autowired
	ResumeRepository resumeRepository;
	
	String compareRole = "Seeker";
	
	Logger logger =LoggerFactory.getLogger(UserServiceImp.class);
	
	@Override
	public boolean createProfile(JobSeeker seeker) {
		
		logger.info("Jobseeker profile creating for: {}",seeker.getSeekerName());
		
//		JobSeeker jobSeeker = new JobSeeker();
//		jobSeeker.setName(seeker.getName());
//		jobSeeker.setEmail(seeker.getEmail());
//		jobSeeker.setAddress(seeker.getAddress());
//		jobSeeker.setCountry(seeker.getCountry());
//		jobSeeker.setPhno(seeker.getPhno());
//		jobSeeker.setCtc(seeker.getCtc());
//		jobSeeker.setDateOfBirth(seeker.getDateOfBirth());
//		jobSeeker.setApplications(seeker.getApplications());
//		jobSeeker.setResume(seeker.getResume());
//		jobSeeker.setSummary(seeker.getSummary());
//		jobSeeker.setTagline(seeker.getTagline());
		
		logger.info("Saving Seeker profile to database: ");
		JobSeeker temp = seekerRepository.save(seeker);
		
		UserInfo currentUser;
		try {
			currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
				currentUser.setRoleId(temp.getSeekerId());
				userInfoRepository.save(currentUser);
				logger.info("Linking user Info with seeker profile: ",seeker.getSeekerName());

			}
		} catch (Exception e) {
	        logger.error("Error occurred while creating profile for job seeker", e);
			e.printStackTrace();
		}
		return temp!=null;
	}

	@Override
	public boolean updateProfile(JobSeeker seeker) {
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
				logger.info("Getting Seeker id from current active user id");
				seeker.setSeekerId(currentUser.getRoleId());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    logger.info("Updating profile for job seeker with ID: {}");
		return seekerRepository.save(seeker) != null;
	}

	@Override
	public boolean editResume(Resume resume) {
		JobSeeker temp = null;
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
				temp = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
				temp.setResume(resume);
				temp = seekerRepository.save(temp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return temp!=null;
	}
	
	@Override
	public List<Listing> searchJobs() {
	    logger.info("Searching for jobs from database");
		return listingRepository.findAll();
	}

	@Override
	public boolean applyForJob(long listingId, Applications application) throws ListingNotFoundException {
		//also add application object to listing object application list
		JobSeeker seeker = null;
		Listing listing = listingRepository.findById(listingId).orElse(null);
		if(listing==null) 
			throw new ListingNotFoundException();
		
		List<Applications> appList = listing.getApplications();
		appList.add(application);
		listing.setApplications(appList);
		appList=null;
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
				seeker = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
				appList = seeker.getApplications();
				appList.add(application);
				seeker.setApplications(appList);
				seeker = seekerRepository.save(seeker);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if(seeker==null)
			return false;
		
		return true;
	}

	@Override
	public List<Applications> getAppliedJobs() {
		
		JobSeeker seeker;
		
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
				seeker  = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
			    logger.info("Fetching applied jobs for current job seeker");
				return seeker.getApplications();
			}
		} catch (Exception e) {
	        logger.error("Error occurred while fetching applied jobs for job seeker", e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String trackStatus(long applicationId) {
		JobSeeker seeker;
		try {
			UserInfo currentUser = getCurrentUserInfo();
			//also check applications seeker id == getRoleId()
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
			    
				logger.info("Tracking status for application with ID: {}", applicationId);
				
			    seeker  = seekerRepository.findById(currentUser.getRoleId()).orElse(null);

				List<Applications> applicationList = seeker.getApplications();
				return applicationList.stream()
						.filter(application -> application.getApplicationId() == applicationId)
						.findFirst().orElse(null).getStatus();
			}
		} catch (Exception e) {
	        logger.error("Error occurred while fetching status for job seeker", e);
			e.printStackTrace();
		}
		return null;
	}

	private UserInfo getCurrentUserInfo() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.isAuthenticated()) {
			
			logger.info("Could not authenticated");

			throw new Exception();
		}
		UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
		return userInfoRepository.findByName(userDetailsImp.getUsername()).orElse(null);
	}
	
	public List<JobSeeker> getAll(){
		return seekerRepository.findAll();
	}

}
