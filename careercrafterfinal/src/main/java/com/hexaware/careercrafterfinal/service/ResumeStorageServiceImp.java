package com.hexaware.careercrafterfinal.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hexaware.careercrafterfinal.config.UserDetailsImp;
import com.hexaware.careercrafterfinal.entities.JobSeeker;
import com.hexaware.careercrafterfinal.entities.ProfilePic;
import com.hexaware.careercrafterfinal.entities.Resume;
import com.hexaware.careercrafterfinal.entities.ResumeDoc;
import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.exception.AuthenticationException;
import com.hexaware.careercrafterfinal.message.ResponseFile;
import com.hexaware.careercrafterfinal.repository.JobSeekerRepository;
import com.hexaware.careercrafterfinal.repository.ResumeDocRepository;
import com.hexaware.careercrafterfinal.repository.ResumeRepository;
import com.hexaware.careercrafterfinal.repository.UserInfoRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ResumeStorageServiceImp implements IResumeStorageService {

	@Autowired
	private ResumeDocRepository resumeDocRepository;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private ResumeRepository resumeRepository;
	
	@Autowired
	private JobSeekerRepository seekerRepository;

	@Override
	public boolean store(MultipartFile file) throws IOException, SerialException, SQLException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    
	    ResumeDoc resumeDoc = new ResumeDoc(fileName, file.getContentType(), new SerialBlob(file.getBytes()));
	    
	    JobSeeker seeker = null;
		try {
			UserInfo currentUser = getCurrentUserInfo();
			if(currentUser.getRole().equalsIgnoreCase("SEEKER")) {
				seeker = seekerRepository.findById(currentUser.getRoleId()).orElse(null);
				Resume crafterResume = seeker.getResume();
				crafterResume.setResumeFile(resumeDoc);
				seekerRepository.save(seeker);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return seeker!=null;
	  }
	  
	  @Override
	  public ResponseEntity<ByteArrayResource> downloadFile(String fileId) throws Exception 
	  {
	      try
	      {
	          ResumeDoc dbFile = getFile(fileId);
	          return ResponseEntity.ok()
	              .contentType(MediaType.parseMediaType(dbFile.getType()))
	              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getName() + "\"")
	              .body(new ByteArrayResource(dbFile.getData().getBytes(1, (int) dbFile.getData().length())));
	      }   
	      catch(Exception e)
	      {
	          throw new Exception("Error downloading file");
	      }
	  }

	  @Override
	  public List<ResponseFile> getListFiles()  {
		    List<ResponseFile> files = getAllFiles().map(dbFile -> {
		      String fileDownloadUri = ServletUriComponentsBuilder
		          .fromCurrentContextPath()
		          .path("/files/")
		          .path(dbFile.getdocId())
		          .toUriString();

		      long blobLength = 0;
	          byte[] data = null;
	          try {
	              blobLength = dbFile.getData().length();
	          } catch (SQLException e) {
	              e.printStackTrace();
	          } 

	          return new ResponseFile(
	                  dbFile.getName(),
	                  fileDownloadUri,
	                  dbFile.getType(),
	                  blobLength);
	      }).collect(Collectors.toList());

	      return files;
		  }
	  
	  @Override
	  public ResponseEntity<ByteArrayResource> getSingleResumeResponseById(long resumeId) throws Exception {
		  Resume resume = resumeRepository.findById(resumeId).orElse(null);
		  ResumeDoc resumeFile = null;
			try
		      {
				UserInfo currentUser = getCurrentUserInfo();
				if(resume.getResumeFile()!=null) {
					resumeFile = resume.getResumeFile();
				}else {
					throw new Exception("Resume File not found");
				}
		          return ResponseEntity.ok()
		              .contentType(MediaType.parseMediaType(resumeFile.getType()))
		              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resumeFile.getName() + "\"")
		              .body(new ByteArrayResource(resumeFile.getData().getBytes(1, (int) resumeFile.getData().length())));
		      }   
		      catch(Exception e)
		      {
		          throw new Exception("Error downloading file");
		      }
	  }
	  
	  @Override
	  public ResponseFile getSingleResumeResponse(String docId) {
		  
		  ResumeDoc resumeFile = getFile(docId);

		  String fileDownloadUri = ServletUriComponentsBuilder
				    .fromCurrentContextPath()
				    .path("/files/")
				    .path(resumeFile.getdocId()) // Assuming getDocId() method exists in the resumeFile object
				    .toUriString();
		  
		  long blobLength = 0;
		  byte[] data = null;
		  try {
		      blobLength = resumeFile.getData().length(); // Assuming getData() method exists in the resumeFile object
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }
		  
		  ResponseFile responseFile = new ResponseFile(
				    resumeFile.getName(),
				    fileDownloadUri,
				    resumeFile.getType(),
				    blobLength
				);
		  
		  return responseFile;
		  
	  }
	  
	  @Override
	  public ResumeDoc getFile(String id) {
	    return resumeDocRepository.findById(id).get();
	  }
	  
	  @Override
	  public Stream<ResumeDoc> getAllFiles() {
	    return resumeDocRepository.findAll().stream();
	  }

	  @Override
	  public String extractTextFromPdf(byte[] pdfData) throws IOException {
	        try (PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfData))) {
	            PDFTextStripper textStripper = new PDFTextStripper();
	            return textStripper.getText(document);
	        }
	    }
	  
//	  public ResponseEntity<List<ByteArrayResource>> downloadAllFiles() {
//	      try {
//	          List<ResumeDoc> allFiles = getAllFiles().toList();
//	          List<ByteArrayResource> resources = new ArrayList<>();
//	          for (ResumeDoc file : allFiles) {
//	              ByteArrayResource resource = new ByteArrayResource(file.getData().getBytes(1, (int) file.getData().length()));
//	              resources.add(resource);
//	          }
//	          return ResponseEntity.ok()
//	                  .body(resources);
//	      } catch (Exception e) {
//	          e.printStackTrace();
//	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                  .body(new ArrayList<ByteArrayResource>());
//	      }
//	  }
	  
	//public List<ResponseEntity<ByteArrayResource>> downloadAllFiles() throws SQLException {
//		List<ResumeDoc> allList = getAllFiles().toList();
//		List<ResponseEntity<ByteArrayResource>> responseList = new ArrayList<>();
//		for(ResumeDoc dbFile:allList) {
//			responseList.add(ResponseEntity.ok()
//	        .contentType(MediaType.parseMediaType(dbFile.getType()))
//	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getName() + "\"")
//	        .body(new ByteArrayResource(dbFile.getData().getBytes(1, (int) dbFile.getData().length()))));
//		}
//		return responseList;
	//}
	//  public ResponseEntity<List<byte[]>> downloadAllFiles() throws SQLException {
//	      try {
//	          List<ResumeDoc> allList = getAllFiles().toList();
//	          List<byte[]> responseList = new ArrayList<>();
//	          for (ResumeDoc dbFile : allList) {
//	              byte[] fileContent = dbFile.getData().getBytes(1, (int) dbFile.getData().length());
//	              responseList.add(fileContent);
//	          }
//	          return ResponseEntity.ok()
//	                  .body(responseList);
//	      } catch (Exception e) {
//	          e.printStackTrace();
//	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                  .body(new ArrayList<byte[]>());
//	      }
	//  }
	  
	  private UserInfo getCurrentUserInfo() throws AuthenticationException {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(!authentication.isAuthenticated()) {

				throw new AuthenticationException();
			}
			UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
			return userInfoRepository.findByName(userDetailsImp.getUsername()).orElse(null);
		}
}
