package com.hexaware.careercrafterfinal.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.careercrafterfinal.dto.JobSeekerDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.JobSeeker;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.exception.ApplicationException;
import com.hexaware.careercrafterfinal.exception.ProfileUpdateException;
import com.hexaware.careercrafterfinal.service.IUserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/seeker")
public class JobSeekerRestController {
	
	Logger logger=LoggerFactory.getLogger(JobSeekerRestController.class);
	@Autowired
	IUserService userService;
	
	@PostMapping("/createprofile")
	@PreAuthorize("hasAuthority('SEEKER')")
	public String createProfile(JobSeekerDto seeker) throws ProfileUpdateException {
        logger.info("Creating profile for seeker: {}");
		if(!userService.createProfile(seeker)) {
			throw new ProfileUpdateException();
		}
		return "Profile created!!";
	}

	@PutMapping("/updateprofile")
	@PreAuthorize("hasAuthority('SEEKER')")
	public String updateProfile(JobSeeker seeker) throws ProfileUpdateException {
        logger.info("Updating profile for seeker with ID: {}");

		if(!userService.updateProfile(seeker)) {
			throw new ProfileUpdateException();
		}
		return "Profile updated!!";
	}
	
	@GetMapping("/searchjobs")
	@PreAuthorize("hasAuthority('SEEKER')")
	public List<Listing> searchJobs(){
        logger.info("Searching for jobs");

		return userService.searchJobs();
	}
	
	@PostMapping("/apply/{listingId}")
	public String applyForJob(@PathVariable long listingId,@RequestBody @Valid Applications application) throws ApplicationException {
        logger.info("Applying for job with listing ID: {}", listingId);

		if(userService.applyForJob(listingId,application)) {
			throw new ApplicationException("Could not send application for this Job listing");
		}
		return "Applied!!"; 
	}

	@GetMapping("/getyourapplications")
	@PreAuthorize("hasAuthority('SEEKER')")
	public List<Applications> getAppliedJobs(JobSeekerDto seeker){
        logger.info("Fetching applied jobs for seeker: {}");

		return userService.getAppliedJobs();
	}
	
	@GetMapping("/trackStatus")
	@PreAuthorize("hasAuthority('SEEKER')")
	public String trackStatus(long applicationId) throws ApplicationException {
        logger.info("Tracking status for application with ID: {}", applicationId);

		String result = userService.trackStatus(applicationId);
		if(result==null) {
			throw new ApplicationException("Application Status could not be fetched");
		}
		return result;
	}
	
}
