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
import com.hexaware.careercrafterfinal.dto.EmployerDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Employer;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.entities.ResumeDoc;
import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.exception.AuthenticationException;
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

	String compareRole = "Employer";
	
	@Override
	public boolean createProfile(EmployerDto emp) {
		UserInfo userInfo;
		try {
			userInfo = getCurrentUserInfo();
			
			Employer employer = new Employer();
			employer.setName(emp.getName());
			employer.setAddress(emp.getAddress());
			employer.setEmployerGender(emp.getEmployerGender());
			employer.setCompanyName(emp.getCompanyName());
			employer.setPhno(emp.getPhno());
			employer.setListings(emp.getListings());
			employer.setEmployerId(emp.getEmployerId());
			
			logger.info("Creating profile for employer: ", employer.getName());
			
			Employer temp = null;
			if(userInfo.getRole().equalsIgnoreCase(compareRole)) {
				
				employer.setEmail(userInfo.getEmail());
				temp = employerRepo.save(employer);
				
				if(temp == null)
					return false;
				
				userInfo.setRoleId(temp.getEmployerId());
				logger.info("Connecting profile for employer with current active user account: ", temp.getEmployerId());

				userInfoRepository.save(userInfo);
			}
			return temp!=null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updateProfile(EmployerDto emp) {
		
		Employer employer = new Employer();
		employer.setName(emp.getName());
		employer.setAddress(emp.getAddress());
		employer.setEmployerGender(emp.getEmployerGender());
		employer.setEmail(emp.getEmail());
		employer.setCompanyName(emp.getCompanyName());
		employer.setPhno(emp.getPhno());
		employer.setListings(emp.getListings());
		employer.setEmployerId(emp.getEmployerId());
		
		
		UserInfo userInfo;
		try {
			userInfo = getCurrentUserInfo();
			if(userInfo.getRole().equalsIgnoreCase(compareRole)) {
				long roleId = userInfo.getRoleId();
				employer.setEmployerId(roleId);
				userInfo.setEmail(employer.getEmail());
				employer.setListings((employerRepo.findById(userInfo.getRoleId()).orElse(null).getListings()));
				userInfoRepository.save(userInfo);
			}
		} catch (Exception e) {
	        logger.error("Error occurred while retrieving current user info.", e);
			e.printStackTrace();
		}
		
		logger.info("Updating profile for employer: ", employer.getEmployerId());
		
		return employerRepo.save(employer) != null;
	}

	@Override
	public boolean postListing(Listing listing) {
		UserInfo userInfo;
		try {
			userInfo = getCurrentUserInfo();
			if(userInfo.getRole().equalsIgnoreCase(compareRole)) {
				listing.setEmployer(employerRepo.findById(userInfo.getRoleId()).orElse(null));
				listing.setListingStatus("Open");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Posting listing with ID: {}", listing.getJd());

		return listingRepository.save(listing)!=null;
	}

	@Override
	public boolean updateListing(long listingId, Listing listing) {
		UserInfo userInfo;
		listing.setListingId(listingId);
		try {
			userInfo = getCurrentUserInfo();
			if(userInfo.getRole().equalsIgnoreCase(compareRole)) {
				listing.setEmployer(employerRepo.findById(userInfo.getRoleId()).orElse(null));
				listing.setApplications((listingRepository.findById(listingId).orElse(null)).getApplications());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Updating Listing with ID: {}", listingId);

		return listingRepository.save(listing)!=null;
	}

	@Override
	public boolean changeListingStatus(long listingId,String status) {
		
	    logger.info("Deleting listing with ID: {}", listingId);

		Listing listing = listingRepository.findById(listingId).orElse(null);
		listing.setListingStatus(status);
		return listingRepository.save(listing)!=null;
	}

	@Override
	public List<Applications> viewApplicationsForListing(long listingId) {
	    logger.info("Fetching all applications for listing id:"+listingId);

	    Listing listing = listingRepository.findById(listingId).orElse(null);

	    List<Applications> res = new ArrayList<>();

	    Applications temp;
	    
	    for(Applications app:listing.getApplications()) {
	    	temp=new Applications();
	    	temp.setApplicationId(app.getApplicationId());
	    	temp.setAppliedDate(app.getAppliedDate());
	    	temp.setCompanyName(app.getCompanyName());
	    	temp.setCoverLetter(app.getCoverLetter());
	    	temp.setProfile(app.getProfile());
	    	
	    	Resume resume = app.getResume();
	    	ResumeDoc tempDoc =  resume.getResumeFile();
	    	if(tempDoc!=null) {
	    		tempDoc.setData(null);
	    	}
	    	resume.setResumeFile(tempDoc);
	    	temp.setResume(resume);
	    	//temp.setResponseFile(app.getResponseFile());
	    	temp.setStatus(app.getStatus());
	    	
	    	res.add(temp);
	    }
	    
		return res;
	}
	
	@Override
	public List<Applications> viewApplications() {
	    logger.info("Fetching all applications");
	    List<Applications> res = new ArrayList<>();

	    Applications temp;
	    
	    for(Applications app:applicationRepo.findAll()) {
	    	temp=new Applications();
	    	temp.setApplicationId(app.getApplicationId());
	    	temp.setAppliedDate(app.getAppliedDate());
	    	temp.setCompanyName(app.getCompanyName());
	    	temp.setCoverLetter(app.getCoverLetter());
	    	temp.setProfile(app.getProfile());
	    	
	    	Resume resume = app.getResume();
	    	ResumeDoc tempDoc =  resume.getResumeFile();
	    	if(tempDoc!=null) {
	    		tempDoc.setData(null);
	    	}
	    	resume.setResumeFile(tempDoc);
	    	temp.setResume(resume);
	    	//temp.setResponseFile(app.getResponseFile());
	    	temp.setStatus(app.getStatus());
	    	
	    	res.add(temp);
	    }

		return res;
	}

	@Override
	public boolean changeApplicationStatus(long applicationId, String status) {
	    logger.info("Changing status of application with ID: {} to {}", applicationId, status);

		return applicationRepo.updateStatus(status,applicationId)>0;
	}

	@Override
	public List<Resume> manageResumeDb() {
	    logger.info("Fetching all resumes");
	    List<Resume> originalList = resumeRepository.findAll();
		List<Resume> resultList = new ArrayList<>();
		Resume resume = null;
		for(Resume r:originalList) {
			resume = new Resume();
			resume.setAccomplishments(r.getAccomplishments());
			resume.setAddress(r.getAddress());
			resume.setCertifications(r.getCertifications());
			resume.setEducation(r.getEducation());
			resume.setExperiences(r.getExperiences());
			resume.setLanguages(r.getLanguages());
			resume.setProjects(r.getProjects());
			resume.setReferenceLinks(r.getReferenceLinks());
			resume.setResumeId(r.getResumeId());
			resume.setSkills(r.getSkills());
			resultList.add(resume);
		}
		return resultList;
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
}
