package com.hexaware.careercrafterfinal.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.careercrafterfinal.dto.EmployerDto;
import com.hexaware.careercrafterfinal.dto.ListingDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.exception.AccountNotCreatedException;
import com.hexaware.careercrafterfinal.exception.ApplicationException;
import com.hexaware.careercrafterfinal.exception.ListingNotCreatedException;
import com.hexaware.careercrafterfinal.exception.ListingNotUpdatedException;
import com.hexaware.careercrafterfinal.service.IEmployerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employer")
public class EmployerRestController {

	@Autowired
	IEmployerService employerService;
	
	@PostMapping("/createprofile")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String createProfile(@RequestBody @Valid EmployerDto employer) throws AccountNotCreatedException {
		if(!employerService.createProfile(employer)) {
			throw new AccountNotCreatedException();
		}
		return "Profile  created!!";
	}
	
	@PutMapping("/updateprofile")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String updateProfile(@RequestBody @Valid EmployerDto employer) throws AccountNotCreatedException {
		if(!employerService.updateProfile(employer)) {
			throw new AccountNotCreatedException();
		}
		return "Profile updated!!";
	}

	@PostMapping("/postlisting")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String postListing(@RequestBody @Valid Listing listing) throws ListingNotCreatedException {
		if(!employerService.postListing(listing)) {
			throw new ListingNotCreatedException();
		}
		return "Listing posted!!";
	}
	
	@PutMapping("/updatelisting/{listingId}")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String updateListing(@PathVariable long listingId, @RequestBody @Valid Listing listing) throws ListingNotUpdatedException {
		if(!employerService.updateListing(listingId,listing)) {
			throw new ListingNotUpdatedException();
		}
		return "Listing updated!!";
	}

	@DeleteMapping("/delete/{listingId}")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String deleteListing(@PathVariable long listingId) throws ListingNotUpdatedException {
		if(!employerService.deleteListing(listingId)) {
			throw new ListingNotUpdatedException();
		}
		return "Listing Deleted!!";
	}
	
	@GetMapping("/viewapplications")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public List<Applications> viewApplications(){
		return employerService.viewApplications();
	}
	
	@GetMapping("/viewforlisting/{listingId}")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public List<Applications> viewApplicationsForListing(@PathVariable long listingId){
		return employerService.viewApplicationsForListing(listingId);
	}
	
	@PutMapping("/changeapplicationstatus/{applicationId}")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String changeApplicationStatus(@PathVariable long applicationId,@RequestBody String status) throws ApplicationException {
		if(!employerService.changeApplicationStatus(applicationId, status)) {
			throw new ApplicationException("Application status could not be changed");
		}
		return "Application status changed";
	}
	
	@GetMapping("/manageresume")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public List<Resume> manageResumeDb(){
		return employerService.manageResumeDb();
	}
}
