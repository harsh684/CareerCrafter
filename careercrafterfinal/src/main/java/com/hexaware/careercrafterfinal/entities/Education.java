package com.hexaware.careercrafterfinal.entities;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
@Entity
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long educationId;
	
	@NotBlank
	private String collegeName;
	@NotBlank
	private String degree;
	@NotBlank
	private String specialization;
	@NotNull
	private LocalDate startdate;
	@NotNull
	private LocalDate endDate;
	@NotNull
	private double percentage;
	
	public Education() {}

	public Education(long educationId, String collegeName, String degree, String specialization, LocalDate startdate,
			LocalDate endDate, double percentage) {
		super();
		this.educationId = educationId;
		this.collegeName = collegeName;
		this.degree = degree;
		this.specialization = specialization;
		this.startdate = startdate;
		this.endDate = endDate;
		this.percentage = percentage;
	}

	public long getEducationId() {
		return educationId;
	}

	public void setEducationId(long educationId) {
		this.educationId = educationId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "Education [educationId=" + educationId + ", collegeName=" + collegeName + ", degree=" + degree
				+ ", specialization=" + specialization + ", startdate=" + startdate + ", endDate=" + endDate
				+ ", percentage=" + percentage + "]";
	}
	
	
	
}
