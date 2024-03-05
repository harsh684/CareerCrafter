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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
@Entity
public class Listing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long listingId;
	@NotBlank
	private String companyName;
	@NotBlank
	private String profile;
	@NotBlank
	private String department;
	@NotBlank
	private String location;
	@Min(0)
	private int experienceReqFrom;
	@Min(0)
	private int experienceReqTo;
	
	private double salary;
	@NotNull
	private LocalDate postDate;
	
	private String listingStatus;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "listingId")
	private List<Skills> reqSkills;
	private String jd;
	private String benefitsProvided;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "listingId")
	private List<Applications> applications;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="employerId")
	private Employer employer;
	
	public Listing() {}

	public Listing(long listingId, @NotBlank String profile, @NotBlank String department, @NotBlank String location,
			@Min(0) int experienceReqFrom, @Min(0) int experienceReqTo, double salary, @NotNull LocalDate postDate,
			String listingStatus, List<Skills> reqSkills, String jd, String benefitsProvided,
			List<Applications> applications, Employer employer, @NotBlank String companyName ) {
		super();
		this.listingId = listingId;
		this.profile = profile;
		this.department = department;
		this.location = location;
		this.experienceReqFrom = experienceReqFrom;
		this.experienceReqTo = experienceReqTo;
		this.salary = salary;
		this.postDate = postDate;
		this.listingStatus = listingStatus;
		this.reqSkills = reqSkills;
		this.jd = jd;
		this.benefitsProvided = benefitsProvided;
		this.applications = applications;
		this.employer = employer;
		this.companyName=companyName;
	}

	public long getListingId() {
		return listingId;
	}

	public void setListingId(long listingId) {
		this.listingId = listingId;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getExperienceReqFrom() {
		return experienceReqFrom;
	}

	public void setExperienceReqFrom(int experienceReqFrom) {
		this.experienceReqFrom = experienceReqFrom;
	}

	public int getExperienceReqTo() {
		return experienceReqTo;
	}

	public void setExperienceReqTo(int experienceReqTo) {
		this.experienceReqTo = experienceReqTo;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public LocalDate getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDate postDate) {
		this.postDate = postDate;
	}

	public String getListingStatus() {
		return listingStatus;
	}

	public void setListingStatus(String listingStatus) {
		this.listingStatus = listingStatus;
	}

	public List<Skills> getReqSkills() {
		return reqSkills;
	}

	public void setReqSkills(List<Skills> reqSkills) {
		this.reqSkills = reqSkills;
	}

	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}

	public String getBenefitsProvided() {
		return benefitsProvided;
	}

	public void setBenefitsProvided(String benefitsProvided) {
		this.benefitsProvided = benefitsProvided;
	}

	public List<Applications> getApplications() {
		return applications;
	}

	public void setApplications(List<Applications> applications) {
		this.applications = applications;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName=companyName;
	}

	@Override
	public String toString() {
		return "Listing [listingId=" + listingId + ", profile=" + profile + ", companyName="+ companyName + ", department=" + department + ", location="
				+ location + ", experienceReqFrom=" + experienceReqFrom + ", experienceReqTo=" + experienceReqTo
				+ ", salary=" + salary + ", postDate=" + postDate + ", listingStatus=" + listingStatus + ", reqSkills="
				+ reqSkills + ", jd=" + jd + ", benefitsProvided=" + benefitsProvided + ", applications=" + applications
				+ ", employer=" + employer + "]";
	}

	
}
