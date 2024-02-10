package com.hexaware.careercrafterfinal.entities;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Component
@Entity
public class Languages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long languageId;
	
	@NotBlank
	private String languageName;

	public Languages() {
		super();
	}

	public Languages(long languageId, String languageName) {
		super();
		this.languageId = languageId;
		this.languageName = languageName;
	}

	public long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(long languageId) {
		this.languageId = languageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	@Override
	public String toString() {
		return "Languages [languageId=" + languageId + ", languageName=" + languageName + "]";
	}
	
	
}
