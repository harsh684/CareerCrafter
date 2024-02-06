package com.hexaware.careercrafterfinal.dto;

import java.util.List;

import com.hexaware.careercrafterfinal.entities.Listing;


public class EmployerDto {

	private long employerId;
	private String name;
	private String email;
	private String phno;
	private String address;
	private String companyName;
	
	
	private List<Listing> listings;
	private String token;
	
	public EmployerDto() {}

	public EmployerDto(long employerId, String name, String email, String phno, String address, String companyName,
			List<Listing> listings, String token) {
		super();
		this.employerId = employerId;
		this.name = name;
		this.email = email;
		this.phno = phno;
		this.address = address;
		this.companyName = companyName;
		this.listings = listings;
		this.token = token;
	}

	public long getemployerId() {
		return employerId;
	}

	public void setemployerId(long employerId) {
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Employer [employerId=" + employerId + ", name=" + name + ", email=" + email + ", phno=" + phno + ", address=" + address
				+ ", companyName=" + companyName + ", listings=" + listings + ", token=" + token + "]";
	}
}