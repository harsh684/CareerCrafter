package com.hexaware.careercrafterfinal.restcontroller;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.careercrafterfinal.service.IProfilePicStorageService;

@RestController
@RequestMapping("/api/profilepic")
public class ProfilePicRestController {

	@Autowired
	private IProfilePicStorageService pictureStorageService;
	
	@PostMapping("/upload")
	@PreAuthorize("hasAnyAuthority('EMPLOYER','SEEKER')")
	public String uploadProfilePic(@RequestParam("profilepic") MultipartFile picture) throws IOException, SerialException, SQLException {
		String message = "";
	    try {
	    	pictureStorageService.uploadProfilePic(picture);

	      message = "Uploaded profile picture successfully: " + picture.getOriginalFilename();
	    } catch (Exception e) {
	      message = "Could not upload profile picture: " + picture.getOriginalFilename() + "!";
	      return message;
	    }
	    return message;
	}
	
	@GetMapping("/getpic")
	public ResponseEntity<ByteArrayResource> getProfilePic() throws Exception{
		  return pictureStorageService.getPic();
	  }
	
}
