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
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Component
@Entity
public class Employer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employerId;
	private String name;
	
	@NotBlank 
	private String employerGender;
	@Email
	private String email;
	@Pattern(regexp="\\d{10}")
	private String phno;
	private String address;
	private String companyName;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "employerId")
	private List<Listing> listings;
	
	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "pictureId")
	private ProfilePic profilePic;
	
	public Employer() {}

	public Employer(long employerId, String name, @NotBlank String employerGender, @Email String email,
			@Pattern(regexp = "\\d{10}") String phno, String address, String companyName, List<Listing> listings,
			ProfilePic profilePic) {
		super();
		this.employerId = employerId;
		this.name = name;
		this.employerGender = employerGender;
		this.email = email;
		this.phno = phno;
		this.address = address;
		this.companyName = companyName;
		this.listings = listings;
		this.profilePic = profilePic;
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

	public String getEmployerGender() {
		return employerGender;
	}

	public void setEmployerGender(String employerGender) {
		this.employerGender = employerGender;
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
