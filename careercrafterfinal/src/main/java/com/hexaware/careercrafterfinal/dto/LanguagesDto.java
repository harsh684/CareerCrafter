package com.hexaware.careercrafterfinal.dto;

public class LanguagesDto {

	private long languageId;
	
	private String languageName;

	public LanguagesDto() {
		super();
	}

	public LanguagesDto(long languageId, String languageName) {
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
