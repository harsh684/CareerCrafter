package com.hexaware.careercrafterfinal.restcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hexaware.careercrafterfinal.entities.ResumeDoc;
import com.hexaware.careercrafterfinal.message.ResponseFile;
import com.hexaware.careercrafterfinal.service.IResumeStorageService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/resumedoc")
public class ResumeFileRestController {

	@Autowired
	private IResumeStorageService storageService;

	  @PostMapping("/seeker/uploadresumedoc")
	  @PreAuthorize("hasAuthority('SEEKER')")
	  public String uploadFile(@RequestParam("resume") MultipartFile file) {
	    String message = "";
	    try {
	      storageService.store(file);

	      message = "Uploaded the resume successfully: " + file.getOriginalFilename();
	    } catch (Exception e) {
	      message = "Could not upload the resume: " + file.getOriginalFilename() + "!";
	      return message;
	    }
	    return message;
	  }

	  @GetMapping("/employer/manageresumedocs")
	  @PreAuthorize("hasAuthority('EMPLOYER')")
	  public List<ResponseFile> getResumeFilesList()  {
	    return storageService.getListFiles();
	  }

	  @GetMapping("/employer/get/{docId}")
	  @PreAuthorize("hasAuthority('EMPLOYER')")
	  public String getFileContent(@PathVariable String docId) throws SQLException, IOException {
	    ResumeDoc fileDB = storageService.getFile(docId);

	    return storageService.extractTextFromPdf(fileDB.getData().getBytes(1, (int) fileDB.getData().length()));
	  }
	  
//	  @GetMapping("/download/{docId}")
////	  @PreAuthorize("hasAuthority('EMPLOYER')")
//	  @PreAuthorize("hasAnyAuthority('EMPLOYER','SEEKER')")
//	  public ResponseEntity<ByteArrayResource> downloadResumeFile(@PathVariable String docId) throws Exception{
//		  return storageService.downloadFile(docId);
//	  }
	  
	  @GetMapping("/download/{docId}")
	  @PreAuthorize("hasAnyAuthority('EMPLOYER','SEEKER')")
	  public ResponseFile downloadResumeFile(@PathVariable String docId) throws Exception{
		  return storageService.getSingleResumeResponse(docId);
	  }
	  
	  
}
