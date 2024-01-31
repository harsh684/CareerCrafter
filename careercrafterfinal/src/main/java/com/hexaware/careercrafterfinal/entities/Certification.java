package com.hexaware.careercrafterfinal.entities;

import java.time.LocalDate;


import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Component
@Entity
public class Certification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Min(1)
	private long certificationId;
	
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotNull
	private LocalDate startDate;
	@NotNull
	private LocalDate endDate;
	
	public Certification() {
		super();
	}

	public Certification(long certificationId, String title, String description, LocalDate startDate,
			LocalDate endDate) {
		super();
		this.certificationId = certificationId;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public long getCertificationId() {
		return certificationId;
	}

	public void setCertificationId(long certificationId) {
		this.certificationId = certificationId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "Certification [certificationId=" + certificationId + ", title=" + title + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	
}
