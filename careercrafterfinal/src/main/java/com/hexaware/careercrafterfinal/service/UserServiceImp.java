package com.hexaware.careercrafterfinal.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.careercrafterfinal.config.UserDetailsImp;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Employer;
import com.hexaware.careercrafterfinal.entities.JobSeeker;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.entities.ResumeDoc;
import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.exception.ApplicationException;
import com.hexaware.careercrafterfinal.exception.AuthenticationException;
import com.hexaware.careercrafterfinal.exception.ListingNotFoundException;
import com.hexaware.careercrafterfinal.exception.ProfileNotFoundException;
import com.hexaware.careercrafterfinal.exception.UserAlreadyExistsException;
import com.hexaware.careercrafterfinal.repository.ApplicationRepository;
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
	ApplicationRepository applicationRepository;
	
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
		if((seekerRepository.findByPhoneNumber(seeker.getPhoneNumber()).orElse(null)==null)) {
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
		UserInfo currentUser = null;
		logger.info("inside update seeker profile");
		try {
				currentUser = getCurrentUserInfo();
				logger.info("Getting Seeker id from current active user id: "+currentUser);
				seeker.setSeekerId(currentUser.getRoleId());
//				currentUser.setEmail(seeker.getEmail());
//				entityManager.merge(seeker);
//				userInfoRepository.save(currentUser);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
		logger.info("Updating profile for job seeker with ID: {}");
	    
		JobSeeker existingSeeker = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
	    
		System.out.println(existingSeeker);
		seeker.setSeekerId(existingSeeker.getSeekerId());
		seeker.setApplications(existingSeeker.getApplications());
		seeker.setResume(existingSeeker.getResume());
		seeker.setProfilePic(existingSeeker.getProfilePic());
		
	    entityManager.merge(seeker);
		return seekerRepository.save(seeker) != null;
	}

	@Override
	public boolean editResume(Resume resume) {
		JobSeeker temp = null;
		Resume seekerResume = null;
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
				temp = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
				logger.info("Updating resume for job seeker in database: {}"+resume);
				seekerResume = temp.getResume();
				if(seekerResume.getResumeFile()!=null) {
					resume.setResumeFile(temp.getResume().getResumeFile());
				}
				resume.setResumeId(seekerResume.getResumeId());
				resume = resumeRepository.save(resume);
//				temp.setResume(resume);
//				temp = seekerRepository.save(temp);
				//	entityManager.merge(temp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		logger.info("Seeker's resume after updating"+seekerResume);
		return seekerResume!=null;
		//return resumeRepository.save(resume)!=null;
	}
	
	@Override
	public Resume getCrafterResume() throws AuthenticationException {
		logger.info("Getting seeker profile");
		UserInfo currentUser = getCurrentUserInfo();
		JobSeeker seeker = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
		Resume resumeTemp = null;
		
		logger.info("Getting resume from seeker profile");
		
		Resume seekerResume = seeker.getResume();
		
		resumeTemp=new Resume();
		resumeTemp.setResumeId(seekerResume.getResumeId());
		resumeTemp.setAccomplishments(seekerResume.getAccomplishments());
		resumeTemp.setAddress(seekerResume.getAddress());
		resumeTemp.setCertifications(seekerResume.getCertifications());
		resumeTemp.setEducation(seekerResume.getEducation());
		resumeTemp.setExperiences(seekerResume.getExperiences());
		resumeTemp.setLanguages(seekerResume.getLanguages());
		resumeTemp.setProjects(seekerResume.getProjects());
		resumeTemp.setReferenceLinks(seekerResume.getReferenceLinks());
		resumeTemp.setSkills(seekerResume.getSkills());
		
		logger.info("Returning Crafter resume");
		
		return resumeTemp;
	}
	
	@Override
	public String getSeekerNameByResumeId(long resumeId) {
		return seekerRepository.getNameByResumeId(resumeId);
	}
	
	@Override
	public Listing getListingByApplicationId(long applicationId) {
	    logger.info("Get listing from database");
	    Listing original = listingRepository.findById(applicationRepository.getListingId(applicationId)).orElse(null);
        Listing temp=new Listing();
        
        		temp=new Listing();
            	temp.setListingId(original.getListingId());
            	temp.setProfile(original.getProfile());
            	temp.setDepartment(original.getDepartment());
            	temp.setLocation(original.getLocation());
            	temp.setExperienceReqFrom(original.getExperienceReqFrom());
            	temp.setExperienceReqTo(original.getExperienceReqTo());
            	temp.setSalary(original.getSalary());
            	temp.setPostDate(original.getPostDate());
            	temp.setReqSkills(original.getReqSkills());
            	temp.setJd(original.getJd());
            	temp.setCompanyName(original.getCompanyName());
            	temp.setListingStatus(original.getListingStatus());
            	temp.setBenefitsProvided(original.getBenefitsProvided());
        
        
	    return temp;
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
            	temp.setCompanyName(e.getCompanyName());
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
				
				if(seeker.getResume()==null) {
					throw new Exception("Resume Not Found!");
				}
				
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
		Resume resumeTemp = null;
		List<Applications> seekerApplications = new ArrayList<>();
	    Applications temp;
	    
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
				seeker  = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
			    logger.info("Fetching applied jobs for current job seeker");
			    
			    for(Applications app:seeker.getApplications()) {
			    	temp=new Applications();
			    	temp.setApplicationId(app.getApplicationId());
			    	temp.setAppliedDate(app.getAppliedDate());
			    	temp.setCompanyName(app.getCompanyName());
			    	temp.setCoverLetter(app.getCoverLetter());
			    	temp.setProfile(app.getProfile());
			    	
			    	Resume resume = app.getResume();
//			    	ResumeDoc tempDoc =  resume.getResumeFile();
			    	
					if(resume!=null) {
		    			resumeTemp=new Resume();
		    			resumeTemp.setResumeId(resume.getResumeId());
		    			resumeTemp.setAccomplishments(resume.getAccomplishments());
		    			resumeTemp.setAddress(resume.getAddress());
		    			resumeTemp.setCertifications(resume.getCertifications());
		    			resumeTemp.setEducation(resume.getEducation());
		    			resumeTemp.setExperiences(resume.getExperiences());
		    			resumeTemp.setLanguages(resume.getLanguages());
		    			resumeTemp.setProjects(resume.getProjects());
		    			resumeTemp.setReferenceLinks(resume.getReferenceLinks());
		    			resumeTemp.setSkills(resume.getSkills());
					}
					
//					seeker.setResume(resumeTemp);
//			    	resume.setResumeFile(tempDoc);
			    	temp.setResume(resumeTemp);
			    	//temp.setResponseFile(app.getResponseFile());
			    	temp.setStatus(app.getStatus());
			    	
			    	seekerApplications.add(temp);
			    }
			    
					return seekerApplications;
			}
		} catch (Exception e) {
	        logger.error("Error occurred while fetching applied jobs for job seeker", e);
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Long> getListingIds(){
		List<Long> listingIdList = null;
		try {
			UserInfo user = getCurrentUserInfo();
			listingIdList = applicationRepository.getListingIds(user.getRoleId());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listingIdList;
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

	@Override
	public JobSeeker getUserProfile(){
		JobSeeker seeker = new JobSeeker();
		Resume resumeTemp = null;
		try {
			UserInfo currentSeeker = getCurrentUserInfo();
			JobSeeker temp = seekerRepository.findById(currentSeeker.getRoleId()).orElse(null);
			
			seeker.setAddress(temp.getAddress());
			seeker.setApplications(filterApplications(temp.getApplications()));
			seeker.setCountry(temp.getCountry());
			seeker.setCurrentSalary(temp.getCurrentSalary());
			seeker.setDateOfBirth(temp.getDateOfBirth());
			seeker.setEmail(temp.getEmail());
			seeker.setPhoneNumber(temp.getPhoneNumber());
			seeker.setSeekerGender(temp.getSeekerGender());
			seeker.setSeekerName(temp.getSeekerName());
			seeker.setSeekerId(temp.getSeekerId());
			seeker.setSummary(temp.getSummary());
			seeker.setTagline(temp.getTagline());
			Resume resume = temp.getResume();
			if(temp.getResume()!=null) {
    			resumeTemp=new Resume();
    			resumeTemp.setResumeId(resume.getResumeId());
    			resumeTemp.setAccomplishments(resume.getAccomplishments());
    			resumeTemp.setAddress(resume.getAddress());
    			resumeTemp.setCertifications(resume.getCertifications());
    			resumeTemp.setEducation(resume.getEducation());
    			resumeTemp.setExperiences(resume.getExperiences());
    			resumeTemp.setLanguages(resume.getLanguages());
    			resumeTemp.setProjects(resume.getProjects());
    			resumeTemp.setReferenceLinks(resume.getReferenceLinks());
    			resumeTemp.setSkills(resume.getSkills());
			}
			
			seeker.setResume(resumeTemp);
			seeker.setProfilePic(null);
			
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
		if(seeker==null) {
			throw new UsernameNotFoundException("Seeker account not found");
		}
		
		return seeker;
	}
	
	private List<Applications> filterApplications(List<Applications> applications){
		List<Applications> res = new ArrayList<>();
		Resume resumeTemp = null;
	    Applications application = null;
	    
	    for(Applications app:applications) {
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
	    return res;
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
