package com.hexaware.careercrafterfinal.dto;

import java.util.List;

import com.hexaware.careercrafterfinal.entities.Accomplishments;
import com.hexaware.careercrafterfinal.entities.Certification;
import com.hexaware.careercrafterfinal.entities.Education;
import com.hexaware.careercrafterfinal.entities.Languages;
import com.hexaware.careercrafterfinal.entities.Project;
import com.hexaware.careercrafterfinal.entities.ReferenceLinks;
import com.hexaware.careercrafterfinal.entities.Skills;
import com.hexaware.careercrafterfinal.entities.WorkExperience;

public class ResumeDto {

	private long resumeId;
	
	private String address;
	
	private List<Languages> languages;
	
	private List<Skills> skills;
	
	private List<ReferenceLinks> referenceLinks;
	
	private List<Accomplishments> accomplishments;
	
	private List<WorkExperience> experiences;
	
	private List<Education> education;
	
	private List<Project> projects;
	
	private List<Certification> certifications;
	
	public ResumeDto() {}

	public ResumeDto(long resumeId, String address, List<Languages> languages, List<Skills> skills,
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
