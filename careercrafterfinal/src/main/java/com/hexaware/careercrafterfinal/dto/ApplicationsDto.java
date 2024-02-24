package com.hexaware.careercrafterfinal.dto;

import java.time.LocalDate;

import com.hexaware.careercrafterfinal.entities.Resume;

public class ApplicationsDto {
	
	private long applicationId;
	
	private String companyName;
	
	private String profile;
	
	private LocalDate appliedDate;
	
	private String status;
	
	private String coverLetter;
	
	private Resume resume;
	
	//listingid
	
	public ApplicationsDto() {
		
	}

	public ApplicationsDto(long applicationId, String companyName, String profile, LocalDate appliedDate, String status,
			String coverLetter, Resume resume) {
		super();
		this.applicationId = applicationId;
		this.companyName = companyName;
		this.profile = profile;
		this.appliedDate = appliedDate;
		this.status = status;
		this.coverLetter = coverLetter;
		this.resume = resume;
	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	@Override
	public String toString() {
		return "ApplicationsDto [applicationId=" + applicationId + ", companyName=" + companyName + ", profile="
				+ profile + ", appliedDate=" + appliedDate + ", status=" + status + ", coverLetter=" + coverLetter
				+ ", resume=" + resume + "]";
	}

	
	
}
