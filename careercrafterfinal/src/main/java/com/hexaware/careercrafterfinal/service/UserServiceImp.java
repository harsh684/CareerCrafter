package com.hexaware.careercrafterfinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hexaware.careercrafterfinal.config.UserDetailsImp;
import com.hexaware.careercrafterfinal.dto.JobSeekerDto;
import com.hexaware.careercrafterfinal.dto.ListingDto;
import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.JobSeeker;
import com.hexaware.careercrafterfinal.entities.Listing;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.repository.JobSeekerRepository;
import com.hexaware.careercrafterfinal.repository.ListingRepository;
import com.hexaware.careercrafterfinal.repository.ResumeRepository;
import com.hexaware.careercrafterfinal.repository.UserInfoRepository;

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
	ResumeRepository resumeRepository;
	
	String compareRole = "Seeker";
	
	
	@Override
	public boolean createProfile(JobSeeker seeker) {
		
		System.out.println("before insert "+seeker);

//		JobSeeker jobSeeker = new JobSeeker();
//		jobSeeker.setName(seeker.getName());
//		jobSeeker.setEmail(seeker.getEmail());
//		jobSeeker.setAddress(seeker.getAddress());
//		jobSeeker.setCountry(seeker.getCountry());
//		jobSeeker.setPhno(seeker.getPhno());
//		jobSeeker.setCtc(seeker.getCtc());
//		jobSeeker.setDateOfBirth(seeker.getDateOfBirth());
//		jobSeeker.setApplications(seeker.getApplications());
//		jobSeeker.setResume(seeker.getResume());
//		jobSeeker.setSummary(seeker.getSummary());
//		jobSeeker.setTagline(seeker.getTagline());
		
		JobSeeker temp = seekerRepository.save(seeker);
		
		UserInfo currentUser;
		try {
			currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
				currentUser.setRoleId(temp.getSeekerId());
				userInfoRepository.save(currentUser);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return temp!=null;
	}

	@Override
	public boolean updateProfile(JobSeeker seeker) {
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
				seeker.setSeekerId(currentUser.getRoleId());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return seekerRepository.save(seeker) != null;
	}

	@Override
	public boolean editResume(Resume resume) {
		JobSeeker temp = null;
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
				temp = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
				temp.setResume(resume);
				temp = seekerRepository.save(temp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return temp!=null;
	}
	
	@Override
	public List<Listing> searchJobs() {
		
		return listingRepository.findAll();
	}

	@Override
	public boolean applyForJob(long listingId, Applications application) {
		
		return false;
	}

	@Override
	public List<Applications> getAppliedJobs() {
		
		JobSeeker seeker;
		
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
				seeker  = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
				return seeker.getApplications();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String trackStatus(long applicationId) {
		JobSeeker seeker;
		try {
			UserInfo currentUser = getCurrentUserInfo();
			//also check applications seeker id == getRoleId()
			if(currentUser.getRole().equalsIgnoreCase(compareRole)) {
				seeker  = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
				//change logic
				return seeker.getApplications().get(1).getStatus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private UserInfo getCurrentUserInfo() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.isAuthenticated()) {
			throw new Exception();
		}
		UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
		return userInfoRepository.findByName(userDetailsImp.getUsername()).orElse(null);
	}
	
	public List<JobSeeker> getAll(){
		return seekerRepository.findAll();
	}

}
