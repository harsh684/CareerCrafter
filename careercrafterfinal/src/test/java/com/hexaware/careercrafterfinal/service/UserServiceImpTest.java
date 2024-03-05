package com.hexaware.careercrafterfinal.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.careercrafterfinal.entities.Accomplishments;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Certification;
import com.hexaware.careercrafterfinal.entities.Education;
import com.hexaware.careercrafterfinal.entities.JobSeeker;
import com.hexaware.careercrafterfinal.entities.Languages;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Project;
import com.hexaware.careercrafterfinal.entities.ReferenceLinks;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.entities.Skills;
import com.hexaware.careercrafterfinal.entities.WorkExperience;
import com.hexaware.careercrafterfinal.exception.ApplicationException;
import com.hexaware.careercrafterfinal.exception.ProfileUpdateException;

@SpringBootTest
class UserServiceImpTest {

	@Autowired
	IUserService seekerService;
	
	@Test
	void testCreateProfile() throws ProfileUpdateException {
//		List<Languages> languages=new ArrayList<>();
//		languages.add(new Languages(1, "English"));
//		List<Skills> skills=new ArrayList<>();
//		skills.add(new Skills(1,"Java Full Stack"));
//		List<ReferenceLinks> referenceLinks=new ArrayList<>();
//		referenceLinks.add(new ReferenceLinks(1,"http://localhost8080/api/testing"));
//		List<Accomplishments> accomplishments=new ArrayList<>();
//		accomplishments.add(new Accomplishments(1,"accomplishment testing"));
//		List<WorkExperience> workExperiences=new ArrayList<>();
//		workExperiences.add(new WorkExperience(1,"Hexaware", LocalDate.parse("2000-10-10"),LocalDate.now(),30000, "Work as a FSD"));
//		List<Education> educations=new ArrayList<>();
//		educations.add(new Education(1,"TiT","B-TECH", "CSE",LocalDate.parse("2019-05-05"),LocalDate.now(),89));
//		List<Project> projects=new ArrayList<>();
//		projects.add(new Project(1,"File Arranger Sytem","used to arrange file ",LocalDate.parse("2023-10-10"),LocalDate.parse("2023-11-11"),"localhost:8989/api/test","localhost:8787/api/test"));
//		List<Certification> certifications=new ArrayList<>();
//		certifications.add(new Certification(1,"Java FSD","Detailed architecure",LocalDate.parse("2023-10-10"),LocalDate.parse("2024-01-01")));
//		Resume resume=new Resume(1,"J21 delhi",languages,skills,referenceLinks,accomplishments,workExperiences,educations,projects,certifications);
//		
//		JobSeeker jobSeeker = new JobSeeker();
//		jobSeeker.setSeekerId(201);
//		jobSeeker.setSeekerName("mark");
//		jobSeeker.setTagline("SE");
//		jobSeeker.setEmail("ap@gmail.com");
//		jobSeeker.setSummary("good to have");
//		jobSeeker.setDateOfBirth(LocalDate.now());
//		jobSeeker.setPhoneNumber("435678934");
//		jobSeeker.setAddress("noida up");
//		jobSeeker.setCountry("India");
//		jobSeeker.setCurrentSalary(600000);
//		jobSeeker.setResume(resume);
//		
//		assertTrue(seekerService.createProfile(jobSeeker));
	}

	@Test
	void testUpdateProfile() throws ProfileUpdateException{
//		List<Languages> languages=new ArrayList<>();
//		languages.add(new Languages(1, "Hindi"));
//		languages.add(new Languages(2, "Telugu"));
//		List<Skills> skills=new ArrayList<>();
//		skills.add(new Skills(1,"Java Full Stack"));
//		skills.add(new Skills(2,"Python"));
//		List<ReferenceLinks> referenceLinks=new ArrayList<>();
//		referenceLinks.add(new ReferenceLinks(1,"http://localhost8080/api/testing"));
//		referenceLinks.add(new ReferenceLinks(2,"http://localhost8080/api/deployment"));
//		List<Accomplishments> accomplishments=new ArrayList<>();
//		accomplishments.add(new Accomplishments(1,"accomplishment testing"));
//		accomplishments.add(new Accomplishments(2,"accomplishment Development"));
//		List<WorkExperience> workExperiences=new ArrayList<>();
//		workExperiences.add(new WorkExperience(1,"Hexaware", LocalDate.parse("2000-10-10"),LocalDate.now(),30000, "Work as a FSD"));
//		List<Education> educations=new ArrayList<>();
//		educations.add(new Education(1,"TiT","B-TECH", "CSE",LocalDate.parse("2019-05-05"),LocalDate.now(),89));
//		List<Project> projects=new ArrayList<>();
//		projects.add(new Project(1,"File Arranger Sytem","used to arrange file ",LocalDate.parse("2023-10-10"),LocalDate.parse("2023-11-11"),"localhost:8989/api/test","localhost:8787/api/test"));
//		List<Certification> certifications=new ArrayList<>();
//		certifications.add(new Certification(1,"Java FSD","Detailed architecure",LocalDate.parse("2023-10-10"),LocalDate.parse("2024-01-01")));
//		Resume resume=new Resume(1,"J21 delhi",languages,skills,referenceLinks,accomplishments,workExperiences,educations,projects,certifications,null);
//		
//		JobSeeker jobSeeker = new JobSeeker();
//		jobSeeker.setSeekerId(201);
//		jobSeeker.setSeekerName("Harsh");
//		jobSeeker.setTagline("SE");
//		jobSeeker.setEmail("ap@gmail.com");
//		jobSeeker.setSummary("good to have");
//		jobSeeker.setDateOfBirth(LocalDate.now());
//		jobSeeker.setPhoneNumber("435678934");
//		jobSeeker.setAddress("noida up");
//		jobSeeker.setCountry("India");
//		jobSeeker.setCurrentSalary(600000);
//		jobSeeker.setResume(resume);
//		
//		assertTrue(seekerService.updateProfile(jobSeeker));
	}
	
	@Test
	void testEditResume() {
//		List<Languages> languages=new ArrayList<>();
//		languages.add(new Languages(1, "Hindi"));
//		languages.add(new Languages(2, "Telugu"));
//		List<Skills> skills=new ArrayList<>();
//		skills.add(new Skills(1,"Java Full Stack"));
//		skills.add(new Skills(2,"Python"));
//		List<ReferenceLinks> referenceLinks=new ArrayList<>();
//		referenceLinks.add(new ReferenceLinks(1,"http://localhost8080/api/testing"));
//		referenceLinks.add(new ReferenceLinks(2,"http://localhost8080/api/deployment"));
//		List<Accomplishments> accomplishments=new ArrayList<>();
//		accomplishments.add(new Accomplishments(1,"accomplishment testing"));
//		accomplishments.add(new Accomplishments(2,"accomplishment Development"));
//		List<WorkExperience> workExperiences=new ArrayList<>();
//		workExperiences.add(new WorkExperience(1,"Hexaware", LocalDate.parse("2000-10-10"),LocalDate.now(),30000, "Work as a FSD"));
//		List<Education> educations=new ArrayList<>();
//		educations.add(new Education(1,"TiT","B-TECH", "CSE",LocalDate.parse("2019-05-05"),LocalDate.now(),89));
//		List<Project> projects=new ArrayList<>();
//		projects.add(new Project(1,"File Arranger Sytem","used to arrange file ",LocalDate.parse("2023-10-10"),LocalDate.parse("2023-11-11"),"localhost:8989/api/test","localhost:8787/api/test"));
//		List<Certification> certifications=new ArrayList<>();
//		certifications.add(new Certification(1,"Java FSD","Detailed architecure",LocalDate.parse("2023-10-10"),LocalDate.parse("2024-01-01")));
//		Resume resume=new Resume(1,"J21 delhi",languages,skills,referenceLinks,accomplishments,workExperiences,educations,projects,certifications);
//	
//		assertTrue(seekerService.editResume(resume));
	}
	
	@Test
	void testSearchJobs() {
		//assertFalse(seekerService.searchJobs().isEmpty());
	}
	
	@Test
	void testApplyForJob() throws ApplicationException{
//		List<Applications> applications=new ArrayList<>();
//		
//		applications.add(new Applications(1,"Hexaware","ASE",LocalDate.parse("2023-10-10"),"Pending","attached"));
//		
//		jobSeeker.setApplications(applications);
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
