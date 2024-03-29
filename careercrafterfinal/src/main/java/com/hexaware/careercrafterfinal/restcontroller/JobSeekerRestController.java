package com.hexaware.careercrafterfinal.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.careercrafterfinal.dto.JobSeekerDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Employer;
import com.hexaware.careercrafterfinal.entities.JobSeeker;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.exception.ApplicationException;
import com.hexaware.careercrafterfinal.exception.ListingNotFoundException;
import com.hexaware.careercrafterfinal.exception.ProfileNotFoundException;
import com.hexaware.careercrafterfinal.exception.ProfileUpdateException;
import com.hexaware.careercrafterfinal.exception.UserAlreadyExistsException;
import com.hexaware.careercrafterfinal.service.IUserService;


import jakarta.validation.Valid;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/seeker")
public class JobSeekerRestController {

	@Autowired
	IUserService userService;
	
	Logger logger=LoggerFactory.getLogger(JobSeekerRestController.class);
	
	@PostMapping("/createprofile")
	@PreAuthorize("hasAuthority('SEEKER')")
	public String createProfile(@RequestBody @Valid JobSeeker seeker) throws ProfileUpdateException, UserAlreadyExistsException {
        logger.info("Creating profile for job seeker: {}");
		if(!userService.createProfile(seeker)) {
			throw new ProfileUpdateException();
		}
		return "Profile created!!";
	}

	@PutMapping("/createprofile/resume")
	@PreAuthorize("hasAuthority('SEEKER')")
	public String editResume(@RequestBody @Valid Resume resume) throws ProfileUpdateException {
		logger.info("Updating resume for job seeker: {}"+resume);
		if(!userService.editResume(resume)) {
			throw new ProfileUpdateException();
		}
		return "Resume Updated";
	}
	
	@GetMapping("/getCrafterResume")
	@PreAuthorize("hasAuthority('SEEKER')")
	public Resume getCrafterResume() throws Exception {
		logger.info("Getting Crafter resume for job seeker profile: {}");
		Resume response = userService.getCrafterResume();
		if(response==null) {
			throw new Exception("Some error occured while fetching resume");
		}
		
		return response;
	}
	
	@GetMapping("/v1/getSeekerNameByResumeId/{resumeId}")
	@PreAuthorize("hasAuthority('SEEKER')")
	public String getSeekerNameByResumeId(@PathVariable long resumeId){
		return userService.getSeekerNameByResumeId(resumeId);
	}
	
	@PutMapping("/updateprofile")
	@PreAuthorize("hasAuthority('SEEKER')")
	public String updateProfile(@RequestBody JobSeeker seeker) throws ProfileUpdateException {
        logger.info("Updating profile for seeker with ID: {}");
		if(!userService.updateProfile(seeker)) {
			throw new ProfileUpdateException();
		}
		return "Profile updated!!";
	}
	
	@GetMapping("/getseeker")
	@PreAuthorize("hasAuthority('SEEKER')")
	public JobSeeker getUserProfile(){
		return userService.getUserProfile();
	}
	
	@GetMapping("/getListingByApplicationId/{applicationId}")
	@PreAuthorize("hasAuthority('SEEKER')")
	public Listing searchJobs(@PathVariable long applicationId){
        logger.info("Getting job post");
		return userService.getListingByApplicationId(applicationId);
	}
	
	@GetMapping("/searchjobs")
	@PreAuthorize("hasAuthority('SEEKER')")
	public List<Listing> searchJobs(){
        logger.info("Searching for jobs");
		return userService.searchJobs();
	}
	
	@PostMapping("/apply/{listingId}")
	@PreAuthorize("hasAuthority('SEEKER')")
	public String applyForJob(@PathVariable long listingId,@RequestBody @Valid Applications application) throws ApplicationException, ListingNotFoundException, ProfileNotFoundException {
        logger.info("Applying for job with listing ID: {}", listingId);

        userService.applyForJob(listingId,application);
		
		return "Applied!!"; 
	}

	@GetMapping("/getyourapplications")
	@PreAuthorize("hasAuthority('SEEKER')")
	public List<Applications> getAppliedJobs(){
        logger.info("Fetching applied jobs for seeker: {}");

		return userService.getAppliedJobs();
	}
	
	@GetMapping("/getAppledListingList")
	@PreAuthorize("hasAuthority('SEEKER')")
	public List<Long> getListingIds(){
		logger.info("Fetching applied jobs jobIds for seeker: {}");

		return userService.getListingIds();
	}
	
	@GetMapping("/trackStatus/{applicationId}")
	@PreAuthorize("hasAuthority('SEEKER')")
	public String trackStatus(@PathVariable @Valid long applicationId) throws ApplicationException {
        logger.info("Tracking status for application with ID: {}", applicationId);

		String result = userService.trackStatus(applicationId);
		if(result==null) {
			throw new ApplicationException("Application Status could not be fetched");
		}
		return result;
	}
	
}
