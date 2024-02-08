package com.hexaware.careercrafterfinal.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.JobSeeker;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.exception.ApplicationException;
import com.hexaware.careercrafterfinal.exception.ProfileUpdateException;

@SpringBootTest
class UserServiceImpTest {

	@Autowired
	IUserService seekerService;
	
	@Test
	void testCreateProfile() throws ProfileUpdateException {
		JobSeeker seeker = new JobSeeker();
		seeker.setSeekerName("John");
		seeker.setTagline("Developer");
		seeker.setEmail("john@gmail.com");
		seeker.setSummary("Good");
		seeker.setDateOfBirth(LocalDate.parse("2023-01-04"));
		seeker.setPhoneNumber("1023475467");
		seeker.setAddress("Banglore, Karnataka");
		seeker.setCountry("India");
		seeker.setCurrentSalary(40000);
		seeker.setResume(null);
		seeker.setApplications(null);
		
		assertTrue(seekerService.createProfile(seeker));
	}

	@Test
	void testUpdateProfile() throws ProfileUpdateException{
//		JobSeeker seeker = new JobSeeker();
//		seeker.setSeekerId(1);
//		seeker.setSeekerName("John");
//		seeker.setTagline("Developer");
//		seeker.setEmail("john@gmail.com");
//		seeker.setSummary("Good");
//		seeker.setDateOfBirth(LocalDate.parse("2023-01-04"));
//		seeker.setPhoneNumber("1023475467");
//		seeker.setAddress("Banglore, Karnataka");
//		seeker.setCountry("India");
//		seeker.setCurrentSalary(40000);
//		seeker.setResume(null);
//		seeker.setApplications(null);
//		
//		assertTrue(seekerService.updateProfile(seeker));
	}
	
	@Test
	void testSearchJobs() {
		//assertFalse(seekerService.searchJobs().isEmpty());
	}
	
	@Test
	void testApplyForJob() throws ApplicationException{
		
	}
	
	@Test
	void testTrackStatus() throws ApplicationException{
		//assertTrue(seekerService.trackStatus(1)!=null);
	}

	@Test
	void testGetAppliedJobs() {
		//assertFalse(seekerService.getAppliedJobs().isEmpty());
	}


}
