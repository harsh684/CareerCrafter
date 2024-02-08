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

	Logger logger = LoggerFactory.getLogger(EmployerRestController.class);

	@Autowired
	IEmployerService employerService;

	@PostMapping("/createprofile")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String createProfile(@RequestBody @Valid EmployerDto employer) throws AccountNotCreatedException {
	    logger.info("Creating profile for employer: {}");

		if (!employerService.createProfile(employer))
		{
			throw new AccountNotCreatedException();
		}
		return "Profile  created!!";
	}

	@PutMapping("/updateprofile")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String updateProfile(@RequestBody @Valid EmployerDto employer) throws AccountNotCreatedException {
	    logger.info("Updating profile for employer: {}");

		if (!employerService.updateProfile(employer)) {
			throw new AccountNotCreatedException();
		}
		return "Profile updated!!";
	}

	@PostMapping("/postlisting")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String postListing(@RequestBody @Valid Listing listing) throws ListingNotCreatedException {
	    logger.info("Posting listing with ID: {}");

		if (!employerService.postListing(listing)) {
			throw new ListingNotCreatedException();
		}
		return "Listing posted!!";
	}

	@PutMapping("/updatelisting/{listingId}")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String updateListing(@PathVariable long listingId, @RequestBody @Valid Listing listing)
			throws ListingNotUpdatedException {
	    logger.info("Updating listing with ID: {}", listingId);

		if (!employerService.updateListing(listingId, listing)) {
			throw new ListingNotUpdatedException();
		}
		return "Listing updated!!";
	}

	@GetMapping("/delete/{listingId}")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String deleteListing(@PathVariable long listingId) throws ListingNotUpdatedException {
	    logger.info("Deleting listing with ID: {}", listingId);

		if (!employerService.deleteListing(listingId)) {
			throw new ListingNotUpdatedException();
		}
		return "Listing Deleted!!";
	}

	@GetMapping("/viewapplications")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public List<Applications> viewApplications() {
	    logger.info("Viewing applications");

		return employerService.viewApplications();
	}

	@PutMapping("/changeapplicationstatus/{applicationId}")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public String changeApplicationStatus(@PathVariable long applicationId, @RequestBody String status)
			throws ApplicationException {
		if (!employerService.changeApplicationStatus(applicationId, status)) {
			throw new ApplicationException("Application status could not be changed");
		}
		return "Application status changed";
	}

	@GetMapping("/manageresume")
	@PreAuthorize("hasAuthority('EMPLOYER')")
	public List<Resume> manageResumeDb() {
	    logger.info("Managing resume database");

		return employerService.manageResumeDb();
	}
}
