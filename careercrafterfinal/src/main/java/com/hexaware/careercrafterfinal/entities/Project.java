package com.hexaware.careercrafterfinal.entities;

import java.time.LocalDate;


import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long projectId;
	@NotBlank
	private String title;
	
	@Lob
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate endDate;
	
	private String referenceLink;

	private String hostedlink;
	
	public Project() {}

	public Project(long projectId, String title, String description, LocalDate startDate, LocalDate endDate,
			String referenceLink, String hostedlink) {
		super();
		this.projectId = projectId;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.referenceLink = referenceLink;
		this.hostedlink = hostedlink;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
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

	public String getReferenceLink() {
		return referenceLink;
	}

	public void setReferenceLink(String referenceLink) {
		this.referenceLink = referenceLink;
	}

	public String getHostedlink() {
		return hostedlink;
	}

	public void setHostedlink(String hostedlink) {
		this.hostedlink = hostedlink;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", title=" + title + ", description=" + description + ", startDate="
				+ startDate + ", endDate=" + endDate + ", referenceLink=" + referenceLink + ", hostedlink=" + hostedlink
				+ "]";
	}
	
	
}
