package com.hexaware.careercrafterfinal.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hexaware.careercrafterfinal.config.UserDetailsImp;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.JobSeeker;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.exception.ApplicationException;
import com.hexaware.careercrafterfinal.exception.AuthenticationException;
import com.hexaware.careercrafterfinal.exception.ListingNotFoundException;
import com.hexaware.careercrafterfinal.exception.ProfileNotFoundException;
import com.hexaware.careercrafterfinal.exception.UserAlreadyExistsException;
import com.hexaware.careercrafterfinal.repository.JobSeekerRepository;
import com.hexaware.careercrafterfinal.repository.ListingRepository;
import com.hexaware.careercrafterfinal.repository.ResumeRepository;
import com.hexaware.careercrafterfinal.repository.UserInfoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
	
	@Autowired
	IResumeStorageService storageService;
	
	@PersistenceContext
	EntityManager entityManager;
	
	String compareRole = "Seeker";
	
	Logger logger = LoggerFactory.getLogger(UserServiceImp.class);
	
	@Override
	public boolean createProfile(JobSeeker seeker) throws UserAlreadyExistsException {
		if(seekerRepository.findByPhoneNumber(seeker.getPhoneNumber()).orElse(null)==null) {
			UserInfo currentUser;
			try {
				
				currentUser = getCurrentUserInfo();
				
				logger.info("Jobseeker profile creating for: {}",seeker.getSeekerName());
		
				logger.info("Saving Seeker profile to database: ");
				seeker.setSeekerName(currentUser.getName());
				seeker.setEmail(currentUser.getEmail());
				entityManager.merge(seeker);
				JobSeeker temp = seekerRepository.save(seeker);
				
			
				if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
					currentUser.setRoleId(temp.getSeekerId());
					userInfoRepository.save(currentUser);
					logger.info("Linking user Info with seeker profile: ",seeker.getSeekerName());

				}
				
				return temp!=null;
				
			} catch (Exception e) {
		        logger.error("Error occurred while creating profile for job seeker", e);
				e.printStackTrace();
			}
		}else {
			throw new UserAlreadyExistsException("Profile already Exists");
		}
		return false;
	}

	@Override
	public boolean updateProfile(JobSeeker seeker) {
		UserInfo currentUser;
		try {
			currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
				logger.info("Getting Seeker id from current active user id");
				seeker.setSeekerId(currentUser.getRoleId());
				currentUser.setEmail(seeker.getEmail());
				userInfoRepository.save(currentUser);
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
				logger.info("Updating resume for job seeker in database: {}"+resume);
				temp.setResume(resume);
				seekerRepository.save(temp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		logger.info("Seeker after updating resume"+temp);
		return temp!=null;
		//return resumeRepository.save(resume)!=null;
	}
	
	@Override
	public List<Listing> searchJobs() {
	    logger.info("Searching for jobs from database");
	    List<Listing> original = listingRepository.findAll();
        Listing temp=null;
        List<Listing> abstractList = new ArrayList<>();
        for(Listing e:original) {
        	if(e.getListingStatus().equalsIgnoreCase("Open")) {
        		temp=new Listing();
            	temp.setListingId(e.getListingId());
            	temp.setProfile(e.getProfile());
            	temp.setDepartment(e.getDepartment());
            	temp.setLocation(e.getLocation());
            	temp.setExperienceReqFrom(e.getExperienceReqFrom());
            	temp.setExperienceReqTo(e.getExperienceReqTo());
            	temp.setSalary(e.getSalary());
            	temp.setPostDate(e.getPostDate());
            	temp.setReqSkills(e.getReqSkills());
            	temp.setJd(e.getJd());
            	temp.setListingStatus(e.getListingStatus());
            	temp.setBenefitsProvided(e.getBenefitsProvided());
            	abstractList.add(temp);
        	}
        }
	    return abstractList;
	}

	@Override
	public boolean applyForJob(long listingId, Applications application) throws ListingNotFoundException,ProfileNotFoundException, ApplicationException {
		//also add application object to listing object application list
		JobSeeker seeker = null;
		
		Listing listing = listingRepository.findById(listingId).orElse(null);
		if(listing==null) 
			throw new ListingNotFoundException();
		
		List<Applications> appList = null;
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
				seeker = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
				logger.info("Getting job seeker details from database: {}"+seeker);

				if(seeker==null) 
					throw new ProfileNotFoundException("Profile Not Found in the database");
				
				application.setResume(seeker.getResume());
//				if(seeker.getResume().getResumeFile()!=null) {
//					application.setResponseFile(storageService.getSingleResumeResponse(
//							seeker.getResume().getResumeFile().getdocId()));
//				}
				logger.info("Setting job seeker resume to application: {}"+application.getResume());

				appList = seeker.getApplications();
				appList.add(application);
				seeker.setApplications(appList);
				logger.info("Setting application list with new application: {}"+seeker.getApplications());
				entityManager.merge(seeker);
				seeker = seekerRepository.save(seeker);
				logger.info("After saving job seeker data in database: {}"+seeker);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		appList = listing.getApplications();
		appList.add(application);
		listing.setApplications(appList);
		entityManager.merge(listing);
		logger.info("After saving listing with applications: ");
		
		if(seeker==null)
			throw new ApplicationException("Could not send application for this Job listing");
		
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

	private UserInfo getCurrentUserInfo() throws AuthenticationException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.isAuthenticated()) {
			
			logger.info("Could not authenticated");

			throw new AuthenticationException();
		}
		UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
		return userInfoRepository.findByName(userDetailsImp.getUsername()).orElse(null);
	}
	
	public List<JobSeeker> getAll(){
		return seekerRepository.findAll();
	}

}
