package com.hexaware.careercrafterfinal.dto;

import java.time.LocalDate;


public class CertificationDto {

	private long certificationId;

	private String title;
	
	private String description;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	public CertificationDto() {
		super();
	}

	public CertificationDto(long certificationId, String title, String description, LocalDate startDate,
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
