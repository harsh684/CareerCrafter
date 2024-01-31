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

@Component
@Entity
public class Employer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employerId;
	private String name;
	private String email;
	private String phno;
	private String address;
	private String comppanyName;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employerId")
	private List<Listing> listings;
	private String token;
	
	public Employer() {}

	public Employer(long employerId, String name, String email, String phno, String address, String comppanyName,
			List<Listing> listings, String token) {
		super();
		this.employerId = employerId;
		this.name = name;
		this.email = email;
		this.phno = phno;
		this.address = address;
		this.comppanyName = comppanyName;
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

	public String getComppanyName() {
		return comppanyName;
	}

	public void setComppanyName(String comppanyName) {
		this.comppanyName = comppanyName;
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
				+ ", comppanyName=" + comppanyName + ", listings=" + listings + ", token=" + token + "]";
	}
	
	
}
