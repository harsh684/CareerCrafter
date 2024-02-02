package com.hexaware.careercrafterfinal.entities;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
@Entity
public class WorkExperience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long experienceId;
	@NotBlank
	@Size(max=50)
	private String companyName;
	@NotNull
	@Past
	@DateTimeFormat(pattern ="dd-MM-yyyy")
	private LocalDate startDate;
	@NotNull
	@Past
	@DateTimeFormat(pattern ="dd-MM-yyyy")
	private LocalDate endDate;
	@NotNull
	@Pattern(regexp = "^[0-9]*$")
	private double salary;
	@Size(max = 200)
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
