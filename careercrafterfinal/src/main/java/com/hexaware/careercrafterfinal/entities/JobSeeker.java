package com.hexaware.careercrafterfinal.entities;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
@Entity
public class JobSeeker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seekerId;
	
	@NotBlank
	private String seekerName;
	
	@NotBlank
	private String tagline;
	
	@NotBlank
	private String seekerGender;
	
	private String email;
	
	private String summary;
	
	@NotNull
	private LocalDate dateOfBirth;
	
	@NotBlank
	private String phoneNumber;
	
	@NotBlank
	private String address;
	
	@NotBlank
	private String country;
	
	private double currentSalary;
	
	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "resumeId")
	private Resume resume;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "seekerId")
	private List<Applications> applications;

	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "pictureId")
	private ProfilePic profilePic;
	
	public JobSeeker() {
		super();
	}

	public JobSeeker(long seekerId, @NotBlank String seekerName, @NotBlank String tagline,
			@NotBlank String seekerGender, String email, String summary, @NotNull LocalDate dateOfBirth,
			@NotBlank String phoneNumber, @NotBlank String address, @NotBlank String country, double currentSalary,
			Resume resume, List<Applications> applications, ProfilePic profilePic) {
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
		this.profilePic = profilePic;
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

	public ProfilePic getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(ProfilePic profilePic) {
		this.profilePic = profilePic;
	}

	@Override
	public String toString() {
		return "JobSeeker [seekerId=" + seekerId + ", seekerName=" + seekerName + ", tagline=" + tagline
				+ ", seekerGender=" + seekerGender + ", email=" + email + ", summary=" + summary + ", dateOfBirth="
				+ dateOfBirth + ", phoneNumber=" + phoneNumber + ", address=" + address + ", country=" + country
				+ ", currentSalary=" + currentSalary + ", resume=" + resume + ", applications=" + applications
				+ ", profilePic=" + profilePic + "]";
	}

	
}
