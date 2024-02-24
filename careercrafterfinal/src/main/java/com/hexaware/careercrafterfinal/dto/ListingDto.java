package com.hexaware.careercrafterfinal.dto;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Employer;
import com.hexaware.careercrafterfinal.entities.Skills;

public class ListingDto {

private long listingId;
	
	private String profile;
	private String department;
	private String location;
	private int experienceReqFrom;
	private int experienceReqTo;
	private double salary;
	private LocalDate postDate;
	
	private List<Skills> reqSkills;
	private String jd;
	private String benefitsProvided;
	
	
	private List<Applications> applications;
	
	private Employer employer;
	
	public ListingDto() {}

	public ListingDto(long listingId, String profile, String department, String location, int experienceReqFrom,
			int experienceReqTo, double salary, LocalDate postDate, List<Skills> reqSkills, String jd,
			String benefitsProvided, List<Applications> applications, Employer employer) {
		super();
		this.listingId = listingId;
		this.profile = profile;
		this.department = department;
		this.location = location;
		this.experienceReqFrom = experienceReqFrom;
		this.experienceReqTo = experienceReqTo;
		this.salary = salary;
		this.postDate = postDate;
		this.reqSkills = reqSkills;
		this.jd = jd;
		this.benefitsProvided = benefitsProvided;
		this.applications = applications;
		this.employer = employer;
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

	@Override
	public String toString() {
		return "ListingDto [listingId=" + listingId + ", profile=" + profile + ", department=" + department
				+ ", location=" + location + ", experienceReqFrom=" + experienceReqFrom + ", experienceReqTo="
				+ experienceReqTo + ", salary=" + salary + ", postDate=" + postDate + ", reqSkills=" + reqSkills
				+ ", jd=" + jd + ", benefitsProvided=" + benefitsProvided + ", applications=" + applications
				+ ", employer=" + employer + "]";
	}

	
}
