package com.hexaware.careercrafterfinal.dto;

public class SkillsDto {

	private long skillId;
	
	private String skillName;

	public SkillsDto() {
		super();
	}

	public SkillsDto(long skillId, String skillName) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
	}

	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	@Override
	public String toString() {
		return "Skills [skillId=" + skillId + ", skillName=" + skillName + "]";
	}
	
}
