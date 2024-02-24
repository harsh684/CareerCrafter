package com.hexaware.careercrafterfinal.dto;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Resume;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class JobSeekerDto {

	private long seekerId;
	
	private String seekerName;
	
	private String tagline;
	
	private String seekerGender;
	
	private String email;
	
	private String summary;
	
	private LocalDate dateOfBirth;
	
	private String phoneNumber;
	
	private String address;
	
	private String country;
	
	private double currentSalary;
	
	private Resume resume;
	
	private List<Applications> applications;

	public JobSeekerDto() {
		super();
	}

	public JobSeekerDto(long seekerId, String seekerName, String tagline, String seekerGender, String email,
			String summary, LocalDate dateOfBirth, String phoneNumber, String address, String country,
			double currentSalary, Resume resume, List<Applications> applications) {
		super();
		this.seekerId = seekerId;
		this.seekerName = seekerName;
		this.tagline = tagline;
		this.seekerGender = seekerGender;
		this.email = email;
		this.summary = summary;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.country = country;
		this.currentSalary = currentSalary;
		this.resume = resume;
		this.applications = applications;
	}

	public long getSeekerId() {
		return seekerId;
	}

	public void setSeekerId(long seekerId) {
		this.seekerId = seekerId;
	}

	public String getSeekerName() {
		return seekerName;
	}

	public void setSeekerName(String seekerName) {
		this.seekerName = seekerName;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getSeekerGender() {
		return seekerGender;
	}

	public void setSeekerGender(String seekerGender) {
		this.seekerGender = seekerGender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getCurrentSalary() {
		return currentSalary;
	}

	public void setCurrentSalary(double currentSalary) {
		this.currentSalary = currentSalary;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public List<Applications> getApplications() {
		return applications;
	}

	public void setApplications(List<Applications> applications) {
		this.applications = applications;
	}

	@Override
	public String toString() {
		return "JobSeekerDto [seekerId=" + seekerId + ", seekerName=" + seekerName + ", tagline=" + tagline
				+ ", seekerGender=" + seekerGender + ", email=" + email + ", summary=" + summary + ", dateOfBirth="
				+ dateOfBirth + ", phoneNumber=" + phoneNumber + ", address=" + address + ", country=" + country
				+ ", currentSalary=" + currentSalary + ", resume=" + resume + ", applications=" + applications + "]";
	}

	
	
}
