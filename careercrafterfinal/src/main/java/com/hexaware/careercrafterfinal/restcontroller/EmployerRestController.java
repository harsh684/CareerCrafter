package com.hexaware.careercrafterfinal.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.careercrafterfinal.dto.EmployerDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Employer;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.exception.AccountNotCreatedException;
import com.hexaware.careercrafterfinal.exception.ApplicationException;
import com.hexaware.careercrafterfinal.exception.AuthenticationException;
import com.hexaware.careercrafterfinal.exception.ListingNotCreatedException;
import com.hexaware.careercrafterfinal.exception.ListingNotUpdatedException;
import com.hexaware.careercrafterfinal.exception.UserAlreadyExistsException;
import com.hexaware.careercrafterfinal.service.IEmployerService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/employer")
public class EmployerRestController {

	@Autowired
	IEmployerService employerService;
	
	@PostMapping("/v1/createprofile")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String createProfile(@RequestBody @Valid EmployerDto employer) throws AccountNotCreatedException, UserAlreadyExistsException {
		if(!employerService.createProfile(employer)) {
			throw new AccountNotCreatedException();
		}
		return "Profile  created!!";
	}
	
	@PutMapping("/v1/updateprofile")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String updateProfile(@RequestBody @Valid EmployerDto employer) throws AccountNotCreatedException {
		if(!employerService.updateProfile(employer)) {
			throw new AccountNotCreatedException();
		}
		return "Profile updated!!";
	}
	
	@GetMapping("/v1/getprofile")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public Employer getProfile() {
		return employerService.getProfile();
	}

	@PostMapping("/v1/postlisting")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String postListing(@RequestBody @Valid Listing listing) throws ListingNotCreatedException {
		if(!employerService.postListing(listing)) {
			throw new ListingNotCreatedException();
		}
		return "Listing posted!!";
	}
	
	@PutMapping("/v1/updatelisting/{listingId}")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String updateListing(@PathVariable long listingId, @RequestBody @Valid Listing listing) throws ListingNotUpdatedException {
		if(!employerService.updateListing(listingId,listing)) {
			throw new ListingNotUpdatedException();
		}
		return "Listing updated!!";
	}

	@PutMapping("/v1/changelistingstatus/{listingId}")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String changeListingStatus(@PathVariable long listingId,@RequestBody String status) throws ListingNotUpdatedException {
		if(!employerService.changeListingStatus(listingId, status)) {
			throw new ListingNotUpdatedException();
		}
		return "Listing Status Changed!!";
	}
	
	@GetMapping("/v1/viewapplications")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public List<Applications> viewApplications(){
		return employerService.viewApplications();
	}
	
	@GetMapping("/v1/viewforlisting/{listingId}")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public List<Applications> viewApplicationsForListing(@PathVariable long listingId){
		return employerService.viewApplicationsForListing(listingId);
	}
	
	@GetMapping("/v1/getemployerListings")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public List<Listing> getEmployerListings(){
		return employerService.getEmployerListings();
	}
	
//	@GetMapping("/v1/getallListings/")
//	@PreAuthorize("hasAuthority('EMPLOYER')")
//	public List<Listing> getAllListings(){
////		PageRequest pr = PageRequest.of(page, pageSize);
//		return employerService.getAllListings();
//	}
	
	@PutMapping("/v1/changeapplicationstatus/{applicationId}")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String changeApplicationStatus(@PathVariable long applicationId,@RequestBody String status) throws ApplicationException {
		if(!employerService.changeApplicationStatus(applicationId, status)) {
			throw new ApplicationException("Application status could not be changed");
		}
		return "Application status changed";
	}
	
	@GetMapping("/v1/getresumebyid/{resumeId}")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public Resume getResumeById(@PathVariable long resumeId){
		return employerService.getResumeById(resumeId);
	}
	
	@GetMapping("/v1/managecrafterresume")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public List<Resume> manageResumeDb(){
		return employerService.manageResumeDb();
	}
	
}
