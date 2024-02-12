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
import jakarta.validation.constraints.NotBlank;

@Component
@Entity
public class Resume {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long resumeId;
	@NotBlank
	private String address;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "resumeId")
	private List<Languages> languages;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "resumeId")
	private List<Skills> skills;
	
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "resumeId")
	private List<ReferenceLinks> referenceLinks;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "resumeId")
	private List<Accomplishments> accomplishments;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "resumeId")
	private List<WorkExperience> experiences;
	
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "resumeId")
	private List<Education> education;
	
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "resumeId")
	private List<Project> projects;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "resumeId")
	private List<Certification> certifications;
	
	public Resume() {}

	public Resume(long resumeId, String address, List<Languages> languages, List<Skills> skills,
			List<ReferenceLinks> referenceLinks, List<Accomplishments> accomplishments,
			List<WorkExperience> experiences, List<Education> education, List<Project> projects,
			List<Certification> certifications) {
		super();
		this.resumeId = resumeId;
		this.address = address;
		this.languages = languages;
		this.skills = skills;
		this.referenceLinks = referenceLinks;
		this.accomplishments = accomplishments;
		this.experiences = experiences;
		this.education = education;
		this.projects = projects;
		this.certifications = certifications;
	}

	public long getResumeId() {
		return resumeId;
	}

	public void setResumeId(long resumeId) {
		this.resumeId = resumeId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Languages> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Languages> languages) {
		this.languages = languages;
	}

	public List<Skills> getSkills() {
		return skills;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	public List<ReferenceLinks> getReferenceLinks() {
		return referenceLinks;
	}

	public void setReferenceLinks(List<ReferenceLinks> referenceLinks) {
		this.referenceLinks = referenceLinks;
	}

	public List<Accomplishments> getAccomplishments() {
		return accomplishments;
	}

	public void setAccomplishments(List<Accomplishments> accomplishments) {
		this.accomplishments = accomplishments;
	}

	public List<WorkExperience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<WorkExperience> experiences) {
		this.experiences = experiences;
	}

	public List<Education> getEducation() {
		return education;
	}

	public void setEducation(List<Education> education) {
		this.education = education;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Certification> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<Certification> certifications) {
		this.certifications = certifications;
	}

	@Override
	public String toString() {
		return "Resume [resumeId=" + resumeId + ", address=" + address + ", languages=" + languages + ", skills="
				+ skills + ", referenceLinks=" + referenceLinks + ", accomplishments=" + accomplishments
				+ ", experiences=" + experiences + ", education=" + education + ", projects=" + projects
				+ ", certifications=" + certifications + "]";
	}

	
	
}
