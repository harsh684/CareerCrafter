package com.hexaware.careercrafterfinal.dto;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.careercrafterfinal.entities.Applications;
import com.hexaware.careercrafterfinal.entities.Resume;


public class JobSeekerDto {

	private long id;
	
	private String name;
	private String tagline;
	private String email;
	private String summary;
	private LocalDate dateOfBirth;
	private String phno;
	private String address;
	private String country;
	private double ctc;
	
	private Resume resume;
	
	private List<Applications> applications;
	private String token;
	
	public JobSeekerDto() {}
	
	public JobSeekerDto(long id, String name, String tagline, String email, String summary, LocalDate dateOfBirth,
			String phno, String address, String country, double ctc, Resume resume,
			List<Applications> applications, String token) {
		super();
		this.id = id;
		this.name = name;
		this.tagline = tagline;
		this.email = email;
		this.summary = summary;
		this.dateOfBirth = dateOfBirth;
		this.phno = phno;
		this.address = address;
		this.country = country;
		this.ctc = ctc;
		this.resume = resume;
		this.applications = applications;
		this.token = token;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
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
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
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
	public double getCtc() {
		return ctc;
	}
	public void setCtc(double ctc) {
		this.ctc = ctc;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "JobSeeker [id=" + id + ", name=" + name + ", tagline=" + tagline + ", email=" + email + ", summary="
				+ summary + ", dateOfBirth=" + dateOfBirth + ", phno=" + phno + ", address=" + address + ", country="
				+ country + ", ctc=" + ctc + ", resume=" + resume + ", applications=" + applications + ", token="
				+ token + "]";
	}
}
