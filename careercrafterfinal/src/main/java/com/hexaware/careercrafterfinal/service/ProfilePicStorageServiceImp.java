package com.hexaware.careercrafterfinal.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.careercrafterfinal.config.UserDetailsImp;
import com.hexaware.careercrafterfinal.entities.Employer;
import com.hexaware.careercrafterfinal.entities.JobSeeker;
import com.hexaware.careercrafterfinal.entities.ProfilePic;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.entities.ResumeDoc;
import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.exception.AuthenticationException;
import com.hexaware.careercrafterfinal.repository.EmployerRepository;
import com.hexaware.careercrafterfinal.repository.JobSeekerRepository;
import com.hexaware.careercrafterfinal.repository.ProfilePictureRepository;
import com.hexaware.careercrafterfinal.repository.UserInfoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProfilePicStorageServiceImp implements IProfilePicStorageService {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private JobSeekerRepository seekerRepository;
	
	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private ProfilePictureRepository pictureRepository;
	
	@Override
	public boolean uploadProfilePic(MultipartFile picture) throws IOException, SerialException, SQLException {
		String fileName = StringUtils.cleanPath(picture.getOriginalFilename());
	    
	    ProfilePic pic = new ProfilePic(fileName, picture.getContentType(), new SerialBlob(picture.getBytes()),null);
	    
	    JobSeeker seeker = null;
	    Employer employer = null;
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase("SEEKER")) {
				seeker = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
				pic.setRole("SEEKER");
				seeker.setProfilePic(pic);
				return seekerRepository.save(seeker)!=null;
			}
			else if(currentUser.getRole().equalsIgnoreCase("EMPLOYER")) {
				employer = employerRepository.findById(currentUser.getRoleId()).orElse(null);
				pic.setRole("EMPLOYER");
				employer.setProfilePic(pic);
				return employerRepository.save(employer)!=null;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ResponseEntity<ByteArrayResource> getPic() throws Exception {
		ProfilePic pic = null;
		try
	      {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase("Seeker")) {
				pic = (seekerRepository.findById(currentUser.getRoleId()).orElse(null)).getProfilePic();
			}else if(currentUser.getRole().equalsIgnoreCase("Employer")) {
				pic = (employerRepository.findById(currentUser.getRoleId()).orElse(null)).getProfilePic();
			}
	          return ResponseEntity.ok()
	              .contentType(MediaType.parseMediaType(pic.getType()))
	              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pic.getName() + "\"")
	              .body(new ByteArrayResource(pic.getData().getBytes(1, (int) pic.getData().length())));
	      }   
	      catch(Exception e)
	      {
	          throw new Exception("Error downloading file");
	      }
	}
	
	private UserInfo getCurrentUserInfo() throws AuthenticationException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.isAuthenticated()) {

			throw new AuthenticationException();
		}
		UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
		return userInfoRepository.findByName(userDetailsImp.getUsername()).orElse(null);
	}

}
