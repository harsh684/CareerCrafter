package com.hexaware.careercrafterfinal.entities;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
@Entity
public class Applications {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long applicationId;
	
	@NotBlank
	private String companyName;
	
	@NotBlank
	private String profile;
	
	@NotNull
	private LocalDate appliedDate;
	
	@NotBlank
	private String status;
	
	private String coverLetter;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "resumeId")
	private Resume resume;
	//listingid
	
	public Applications() {
		
	}

	public Applications(long applicationId, String companyName, String profile, LocalDate appliedDate, String status,
			String coverLetter) {
		super();
		this.applicationId = applicationId;
		this.companyName = companyName;
		this.profile = profile;
		this.appliedDate = appliedDate;
		this.status = status;
		this.coverLetter = coverLetter;
	}

	
	public Applications(@NotBlank String companyName, @NotBlank String profile, @NotNull LocalDate appliedDate,
			@NotBlank String status, String coverLetter, Resume resume) {
		super();
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
		return "Applications [applicationId=" + applicationId + ", companyName=" + companyName + ", profile=" + profile
				+ ", appliedDate=" + appliedDate + ", status=" + status + ", coverLetter=" + coverLetter + ", resume="
				+ resume + "]";
	}

	
	
	
}
