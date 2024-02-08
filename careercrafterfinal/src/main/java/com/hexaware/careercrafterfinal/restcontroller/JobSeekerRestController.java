package com.hexaware.careercrafterfinal.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.careercrafterfinal.dto.JobSeekerDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.JobSeeker;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.exception.ApplicationException;
import com.hexaware.careercrafterfinal.exception.ProfileUpdateException;
import com.hexaware.careercrafterfinal.service.IUserService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/seeker")
public class JobSeekerRestController {

	@Autowired
	IUserService userService;
	
	@PostMapping("/createprofile")
	@PreAuthorize("hasAuthority('SEEKER')")
	public String createProfile(@RequestBody @Valid JobSeeker seeker) throws ProfileUpdateException {
		if(!userService.createProfile(seeker)) {
			throw new ProfileUpdateException();
		}
		return "Profile created!!";
	}

	@PostMapping("/createprofile/resume")
	@PreAuthorize("hasAuthority('SEEKER')")
	public String editResume(@RequestBody @Valid Resume resume) throws ProfileUpdateException {
		if(!userService.editResume(resume)) {
			throw new ProfileUpdateException();
		}
		return "Resume Updated";
	}
	
	@PutMapping("/updateprofile")
	@PreAuthorize("hasAuthority('SEEKER')")
	public String updateProfile(@RequestBody @Valid JobSeeker seeker) throws ProfileUpdateException {
		if(!userService.updateProfile(seeker)) {
			throw new ProfileUpdateException();
		}
		return "Profile updated!!";
	}
	
	@GetMapping("/getallusers")
	@PreAuthorize("hasAuthority('SEEKER')")
	public List<JobSeeker> getAll(){
		return userService.getAll();
	}
	
	@GetMapping("/searchjobs")
	@PreAuthorize("hasAuthority('SEEKER')")
	public List<Listing> searchJobs(){
		return userService.searchJobs();
	}
	
	@PostMapping("/apply/{listingId}")
	public String applyForJob(@PathVariable long listingId,@RequestBody @Valid Applications application) throws ApplicationException {
		if(userService.applyForJob(listingId,application)) {
			throw new ApplicationException("Could not send application for this Job listing");
		}
		return "Applied!!"; 
	}

	@GetMapping("/getyourapplications")
	@PreAuthorize("hasAuthority('SEEKER')")
	public List<Applications> getAppliedJobs(JobSeekerDto seeker){
		return userService.getAppliedJobs();
	}
	
	@GetMapping("/trackStatus/{applicationId}")
	@PreAuthorize("hasAuthority('SEEKER')")
	public String trackStatus(@PathVariable @Valid long applicationId) throws ApplicationException {
		String result = userService.trackStatus(applicationId);
		if(result==null) {
			throw new ApplicationException("Application Status could not be fetched");
		}
		return result;
	}
	
}
