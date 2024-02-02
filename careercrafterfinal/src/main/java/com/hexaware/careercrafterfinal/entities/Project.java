package com.hexaware.careercrafterfinal.entities;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Component
@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long projectId;
	
	@NotBlank
	@Size(max=255)
	private String title;
	@NotBlank
	@Size(max=1000)
	private String description;
	@NotNull
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate startDate;
	@NotNull
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate endDate;
	@Size(max=255)
	private String referenceLink;
	@Size(max=255)
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
