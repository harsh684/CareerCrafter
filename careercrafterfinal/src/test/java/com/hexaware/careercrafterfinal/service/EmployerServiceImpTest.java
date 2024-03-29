package com.hexaware.careercrafterfinal.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.careercrafterfinal.dto.EmployerDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Skills;
import com.hexaware.careercrafterfinal.exception.ListingNotCreatedException;
import com.hexaware.careercrafterfinal.exception.ListingNotUpdatedException;

@SpringBootTest
class EmployerServiceImpTest {

	@Autowired
	IEmployerService employerService;
	
	@Test
	void testCreateProfile() {
		
//		 // Create a list of skills for the first listing
//        List<Skills> skillsList1 = new ArrayList<>();
//        skillsList1.add(new Skills(1, "Java"));
//        skillsList1.add(new Skills(2, "Spring"));
//
//        // Create a list of applications for the first listing
//        List<Applications> applicationsList1 = new ArrayList<>();
//        applicationsList1.add(new Applications(1, "ABC Company", "Software Engineer", LocalDate.now(), "Pending", "Cover Letter"));
//
//        // Create the first listing
//        Listing listing1 = new Listing();
//        listing1.setListingId(1);
//        listing1.setProfile("Software Engineer");
//        listing1.setDepartment("IT");
//        listing1.setLocation("Location1");
//        listing1.setExperienceReqFrom(2);
//        listing1.setExperienceReqTo(5);
//        listing1.setSalary(80000);
//        listing1.setPostDate(LocalDate.now());
//        listing1.setReqSkills(skillsList1);
//        listing1.setJd("Job Description 1");
//        listing1.setBenefitsProvided("Benefits 1");
//        listing1.setApplications(applicationsList1);
//        
//        // Create a list of skills for the second listing
//        List<Skills> skillsList2 = new ArrayList<>();
//        skillsList2.add(new Skills(3, "Python"));
//        skillsList2.add(new Skills(4, "Django"));
//
//        // Create a list of applications for the second listing
//        List<Applications> applicationsList2 = new ArrayList<>();
//        applicationsList2.add(new Applications(2, "XYZ Company", "Python Developer", LocalDate.now(), "Pending", "Cover Letter"));
//
//        // Create the second listing
//        Listing listing2 = new Listing();
//        listing2.setListingId(2);
//        listing2.setProfile("Python Developer");
//        listing2.setDepartment("IT");
//        listing2.setLocation("Location2");
//        listing2.setExperienceReqFrom(3);
//        listing2.setExperienceReqTo(6);
//        listing2.setSalary(90000);
//        listing2.setPostDate(LocalDate.now());
//        listing2.setReqSkills(skillsList2);
//        listing2.setJd("Job Description 2");
//        listing2.setBenefitsProvided("Benefits 2");
//        listing2.setApplications(applicationsList2);
//        
//        
//        List<Listing> listingList = new ArrayList<>();
//        listingList.add(listing1);
//        listingList.add(listing2);
        
//        EmployerDto employer = new EmployerDto();
//        employer.setName("John Doe");
//        employer.setEmail("john.doe@example.com");
//        employer.setPhno("1234567890");
//        employer.setAddress("123 Main St");
//        employer.setComppanyName("XYZ Corp");
//        employer.setListings(null); 
//        employer.setToken("abc123");
//        
//        assertTrue(employerService.createProfile(employer));
	}

	@Test
	void testUpdateProfile() {
//		EmployerDto employer = new EmployerDto();
//		employer.setemployerId(1);
//	    employer.setName("Harsh Dangi");
//	    employer.setEmail("john.doe@example.com");
//	    employer.setPhno("1234567890");
//	    employer.setAddress("123 Main St");
//	    employer.setComppanyName("XYZ Corp");
//	    employer.setListings(null); 
//	    employer.setToken("abc123");
//	       
//	    assertTrue(employerService.updateProfile(employer.getemployerId(),employer));
	}

	@Test
	void testPostListing() throws ListingNotCreatedException {
		
		List<Skills> skillsList1 = new ArrayList<>();
	    skillsList1.add(new Skills("Java"));
	    skillsList1.add(new Skills("Spring Boot"));
	    
		Listing listing1 = new Listing();
		listing1.setProfile("Software Engineer");
		listing1.setDepartment("IT");
		listing1.setLocation("Location1");
		listing1.setExperienceReqFrom(2);
		listing1.setExperienceReqTo(5);
		listing1.setSalary(80000);
		listing1.setPostDate(LocalDate.now());
		listing1.setReqSkills(skillsList1);
		listing1.setJd("Job Description 1");
		listing1.setBenefitsProvided("Benefits 1");
		listing1.setApplications(null);
		
		assertTrue(employerService.postListing(listing1));
	}

	@Test
	void testUpdateListing() throws ListingNotUpdatedException {
//		List<Skills> skillsList1 = new ArrayList<>();
//	    skillsList1.add(new Skills("Java"));
//	    skillsList1.add(new Skills("Spring Boot"));
//		
//	    List<Applications> applicationsList2 = new ArrayList<>();
//	    applicationsList2.add(new Applications(3, "ABC Company", "Java Developer", LocalDate.now(), "Pending", "Cover Letter"));
//	    
//		Listing listing1 = new Listing();
//		listing1.setListingId(1);
//		listing1.setProfile("Graduate Engineer Trainee");
//		listing1.setDepartment("IT");
//		listing1.setLocation("LocationUpdated");
//		listing1.setExperienceReqFrom(0);
//		listing1.setExperienceReqTo(4);
//		listing1.setSalary(40000);
//		listing1.setPostDate(LocalDate.now());
//		listing1.setReqSkills(null);
//		listing1.setJd("Job Description 100");
//		listing1.setBenefitsProvided("Benefits 2");
//		listing1.setApplications(null);
//		
//		assertTrue(employerService.updateListing(listing1.getListingId(), listing1));
	}

	@Test
	void testDeleteListing() throws ListingNotUpdatedException {
		//assertTrue(employerService.deleteListing(1));
	}

	@Test
	void testViewApplications() {
		//assertTrue(employerService.viewApplications().isEmpty());
	}

	@Test
	void testChangeApplicationStatus() {
		//assertTrue(employerService.changeApplicationStatus(0, null));
	}

}
