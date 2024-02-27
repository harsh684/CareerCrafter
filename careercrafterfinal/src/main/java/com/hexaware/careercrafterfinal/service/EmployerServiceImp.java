package com.hexaware.careercrafterfinal.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import com.hexaware.careercrafterfinal.exception.UserAlreadyExistsException;
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
	public boolean createProfile(EmployerDto emp) throws UserAlreadyExistsException {
		
		if(employerRepo.findByPhno(emp.getPhno()).orElse(null)==null) {
			
			UserInfo userInfo;
			try {
				userInfo = getCurrentUserInfo();
				
				Employer employer = new Employer();
				employer.setAddress(emp.getAddress());
				employer.setEmployerGender(emp.getEmployerGender());
				employer.setCompanyName(emp.getCompanyName());
				employer.setPhno(emp.getPhno());
//				employer.setListings(emp.getListings());
				employer.setEmployerId(emp.getEmployerId());
				
				logger.info("Creating profile for employer: ", employer.getName());
				
				Employer temp = null;
				if(userInfo.getRole().equalsIgnoreCase(compareRole)) {
					
					employer.setName(userInfo.getName());
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
		}else {
			throw new UserAlreadyExistsException("Profile already exists");
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
//		employer.setListings(emp.getListings());
		employer.setEmployerId(emp.getEmployerId());
		
		
		UserInfo userInfo;
		try {
			userInfo = getCurrentUserInfo();
			if(userInfo.getRole().equalsIgnoreCase(compareRole)) {
				long roleId = userInfo.getRoleId();
				employer.setEmployerId(roleId);
				employer.setName(userInfo.getName());
				employer.setEmail(userInfo.getEmail());
				employer.setProfilePic(employerRepo.findById(userInfo.getRoleId()).orElse(null).getProfilePic());
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
	    Resume resumeTemp = null;
	    Applications application = null;
	    
	    for(Applications app:listing.getApplications()) {
    		application=new Applications();
    		application.setApplicationId(app.getApplicationId()); // Set the applicationId
    		application.setCompanyName(app.getCompanyName()); // Set the companyName
    		application.setProfile(app.getProfile()); // Set the profile
    		application.setAppliedDate(app.getAppliedDate()); // Set the appliedDate
    		application.setStatus(app.getStatus()); // Set the status
    		application.setCoverLetter(app.getCoverLetter());
    		if(app.getResume()!=null) {
    			resumeTemp=new Resume();
    			resumeTemp.setResumeFile(null);
    			resumeTemp.setResumeId(app.getResume().getResumeId());
    			resumeTemp.setAccomplishments(app.getResume().getAccomplishments());
    			resumeTemp.setAddress(app.getResume().getAddress());
    			resumeTemp.setCertifications(app.getResume().getCertifications());
    			resumeTemp.setEducation(app.getResume().getEducation());
    			resumeTemp.setExperiences(app.getResume().getExperiences());
    			resumeTemp.setLanguages(app.getResume().getLanguages());
    			resumeTemp.setProjects(app.getResume().getProjects());
    			resumeTemp.setReferenceLinks(app.getResume().getReferenceLinks());
    			resumeTemp.setSkills(app.getResume().getSkills());
    			application.setResume(resumeTemp);
    		}
    		res.add(application);
	    }
//	    for(Applications app:listing.getApplications()) {
//	    	temp=new Applications();
//	    	temp.setApplicationId(app.getApplicationId());
//	    	temp.setAppliedDate(app.getAppliedDate());
//	    	temp.setCompanyName(app.getCompanyName());
//	    	temp.setCoverLetter(app.getCoverLetter());
//	    	temp.setProfile(app.getProfile());
//	    	
//	    	Resume resume = app.getResume();
//	    	ResumeDoc tempDoc =  resume.getResumeFile();
//	    	if(tempDoc!=null) {
//	    		tempDoc.setData(null);
//	    	}
//	    	resume.setResumeFile(tempDoc);
//	    	temp.setResume(resume);
//	    	//temp.setResponseFile(app.getResponseFile());
//	    	temp.setStatus(app.getStatus());
//	    	
//	    	res.add(temp);
//	    }
	    
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
	public Resume getResumeById(long resumeId){
		logger.info("Fetching resume");
	    Resume r = resumeRepository.findById(resumeId).orElse(null);
		Resume resume = new Resume();
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
		
		return resume;
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
	
	@Override
	public Employer getProfile() {
		Employer employer = new Employer();
		try {
			UserInfo currentEmployer = getCurrentUserInfo();
			Employer temp = employerRepo.findById(currentEmployer.getRoleId()).orElse(null);
			
			employer.setName(temp.getName());
			employer.setAddress(temp.getAddress());
			employer.setCompanyName(temp.getCompanyName());
			employer.setEmail(temp.getEmail());
			employer.setPhno(temp.getPhno());
			employer.setEmployerGender(temp.getEmployerGender());
//			employer.setListings(temp.getListings());
			employer.setProfilePic(null);
			
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
		if(employer==null) {
			throw new UsernameNotFoundException("Employer account not found");
		}
		
		return employer;
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

	@Override
	public List<Listing> getEmployerListings() {
		
		List<Listing> original = listingRepository.findAll();
		Listing temp = null;
		Resume resumeTemp = null;
		List<Listing> abstractList = new ArrayList<>();
		List<Applications> applications = new ArrayList();
		Applications application = null;
		try {
			UserInfo user = getCurrentUserInfo();
			Employer employer = employerRepo.findById(user.getRoleId()).orElse(null);
		
			for(Listing e:original) {
		        if(e.getEmployer().getEmployerId()==user.getRoleId()) {
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
		            	temp.setCompanyName(e.getCompanyName());
		            	temp.setListingStatus(e.getListingStatus());
		            	temp.setBenefitsProvided(e.getBenefitsProvided());
		            	for(Applications app:e.getApplications()) {
		            		application=new Applications();
		            		application.setApplicationId(app.getApplicationId()); // Set the applicationId
		            		application.setCompanyName(app.getCompanyName()); // Set the companyName
		            		application.setProfile(app.getProfile()); // Set the profile
		            		application.setAppliedDate(app.getAppliedDate()); // Set the appliedDate
		            		application.setStatus(app.getStatus()); // Set the status
		            		application.setCoverLetter(app.getCoverLetter());
		            		if(app.getResume()!=null) {
		            			resumeTemp=new Resume();
		            			resumeTemp.setResumeFile(null);
		            			resumeTemp.setResumeId(app.getResume().getResumeId());
		            			resumeTemp.setAccomplishments(app.getResume().getAccomplishments());
		            			resumeTemp.setAddress(app.getResume().getAddress());
		            			resumeTemp.setCertifications(app.getResume().getCertifications());
		            			resumeTemp.setEducation(app.getResume().getEducation());
		            			resumeTemp.setExperiences(app.getResume().getExperiences());
		            			resumeTemp.setLanguages(app.getResume().getLanguages());
		            			resumeTemp.setProjects(app.getResume().getProjects());
		            			resumeTemp.setReferenceLinks(app.getResume().getReferenceLinks());
		            			resumeTemp.setSkills(app.getResume().getSkills());
		            			application.setResume(resumeTemp);
		            		}
		            		applications.add(application);
		            	}
		            	temp.setApplications(applications);
		            	abstractList.add(temp);
		        	}
		        }
			}catch(Exception e) {
				e.printStackTrace();
			}
		return abstractList;
	}
	
//	@Override
//	public List<Listing> getAllListings() {
//		
//		List<Listing> original = listingRepository.findAll();
//		Listing temp = null;
//		Resume resumeTemp = null;
//		List<Listing> abstractList = new ArrayList<>();
//		List<Applications> applications = new ArrayList();
//		Applications application = null;
//		
//			for(Listing e:original) {
//		        		temp=new Listing();
//		            	temp.setListingId(e.getListingId());
//		            	temp.setProfile(e.getProfile());
//		            	temp.setDepartment(e.getDepartment());
//		            	temp.setLocation(e.getLocation());
//		            	temp.setExperienceReqFrom(e.getExperienceReqFrom());
//		            	temp.setExperienceReqTo(e.getExperienceReqTo());
//		            	temp.setSalary(e.getSalary());
//		            	temp.setPostDate(e.getPostDate());
//		            	temp.setReqSkills(e.getReqSkills());
//		            	temp.setJd(e.getJd());
//		            	temp.setCompanyName(e.getCompanyName());
//		            	temp.setListingStatus(e.getListingStatus());
//		            	temp.setBenefitsProvided(e.getBenefitsProvided());
//		            	for(Applications app:e.getApplications()) {
//		            		application=new Applications();
//		            		application.setApplicationId(app.getApplicationId()); // Set the applicationId
//		            		application.setCompanyName(app.getCompanyName()); // Set the companyName
//		            		application.setProfile(app.getProfile()); // Set the profile
//		            		application.setAppliedDate(app.getAppliedDate()); // Set the appliedDate
//		            		application.setStatus(app.getStatus()); // Set the status
//		            		application.setCoverLetter(app.getCoverLetter());
//		            		if(app.getResume()!=null) {
//		            			resumeTemp=new Resume();
//		            			resumeTemp.setResumeFile(null);
//		            			resumeTemp.setResumeId(app.getResume().getResumeId());
//		            			resumeTemp.setAccomplishments(app.getResume().getAccomplishments());
//		            			resumeTemp.setAddress(app.getResume().getAddress());
//		            			resumeTemp.setCertifications(app.getResume().getCertifications());
//		            			resumeTemp.setEducation(app.getResume().getEducation());
//		            			resumeTemp.setExperiences(app.getResume().getExperiences());
//		            			resumeTemp.setLanguages(app.getResume().getLanguages());
//		            			resumeTemp.setProjects(app.getResume().getProjects());
//		            			resumeTemp.setReferenceLinks(app.getResume().getReferenceLinks());
//		            			resumeTemp.setSkills(app.getResume().getSkills());
//		            			application.setResume(resumeTemp);
//		            		}
//		            		applications.add(application);
//		            	}
//		            	temp.setApplications(applications);
//		            	abstractList.add(temp);
//		        	}
//		return abstractList;
//	}
}
