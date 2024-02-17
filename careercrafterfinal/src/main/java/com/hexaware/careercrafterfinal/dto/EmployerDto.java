package com.hexaware.careercrafterfinal.dto;

import java.util.List;

import com.hexaware.careercrafterfinal.entities.Listing;


public class EmployerDto {

	private long employerId;
	private String name;
	private String email;
	private String employerGender;
	private String phno;
	private String address;
	private String companyName;
	
	
	private List<Listing> listings;
	
	public EmployerDto() {}

	public EmployerDto(long employerId, String name,String employerGender, String email, String phno, String address, String companyName,
			List<Listing> listings) {
		super();
		this.employerId = employerId;
		this.name = name;
		this.employerGender=employerGender;
		this.email = email;
		this.phno = phno;
		this.address = address;
		this.companyName = companyName;
		this.listings = listings;
	}


	public String getEmployerGender() {
		return employerGender;
	}

	public void setEmployerGender(String employerGender) {
		this.employerGender = employerGender;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public long getEmployerId() {
		return employerId;
	}

	public void setEmployerId(long employerId) {
		this.employerId = employerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setComppanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<Listing> getListings() {
		return listings;
	}

	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}


	@Override
	public String toString() {
		return "Employer [employerId=" + employerId + ", name=" + name + ", email=" + email + ", phno=" + phno + ", address=" + address
				+ ", companyName=" + companyName + ", listings=" + listings + ", token=" + "]";
	}
}
