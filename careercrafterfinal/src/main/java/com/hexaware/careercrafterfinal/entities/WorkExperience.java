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
public class WorkExperience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long experienceId;
	
	@NotBlank
	private String companyName;
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate endDate;
	
	private double salary;
	private String description;
	
	public WorkExperience() {}

	public WorkExperience(long experienceId, String companyName, LocalDate startDate, LocalDate endDate, double salary,
			String description) {
		super();
		this.experienceId = experienceId;
		this.companyName = companyName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.salary = salary;
		this.description = description;
	}

	public long getExperienceId() {
		return experienceId;
	}

	public void setExperienceId(long experienceId) {
		this.experienceId = experienceId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "WorkExperience [experienceId=" + experienceId + ", companyName=" + companyName + ", startDate="
				+ startDate + ", endDate=" + endDate + ", salary=" + salary + ", description=" + description + "]";
	}
	
	

}
