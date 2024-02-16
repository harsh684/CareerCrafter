package com.hexaware.careercrafterfinal.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Stream;

import javax.sql.rowset.serial.SerialException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.careercrafterfinal.entities.ResumeDoc;

public interface IResumeStorageService {

	 public boolean store(MultipartFile file) throws IOException, SerialException, SQLException ;
		  
	 public ResponseEntity<ByteArrayResource> downloadFile(String fileId) throws Exception ;

	 public ResumeDoc getFile(String id);
		  
	 public Stream<ResumeDoc> getAllFiles();

	 public String extractTextFromPdf(byte[] pdfData) throws IOException;
		       
}
