package com.hexaware.careercrafterfinal.entities;

import java.util.List;


import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Component
@Entity
public class Employer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employerId;
	private String name;
	
	@Email
	private String email;
	@Pattern(regexp="\\d{10}")
	private String phno;
	private String address;
	private String companyName;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "employerId")
	private List<Listing> listings;
	
	public Employer() {}

	public Employer(long employerId, String name, String email, String phno, String address, String companyName,
			List<Listing> listings) {
		super();
		this.employerId = employerId;
		this.name = name;
		this.email = email;
		this.phno = phno;
		this.address = address;
		this.companyName = companyName;
		this.listings = listings;
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

	public void setCompanyName(String companyName) {
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
				+ ", comppanyName=" + companyName + ", listings=" + listings + ", token=" + "]";
	}
	
	
}
