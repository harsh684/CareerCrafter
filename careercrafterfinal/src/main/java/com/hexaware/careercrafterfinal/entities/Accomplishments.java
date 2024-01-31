package com.hexaware.careercrafterfinal.entities;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;

@Component
@Entity
public class Accomplishments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Min(1)
	private long accomplishmentId;
	
	private String description;
	
	public Accomplishments() {
		super();
	}
	
	public Accomplishments(long accomplishmentId, String description) {
		super();
		this.accomplishmentId = accomplishmentId;
		this.description = description;
	}
	public long getAccomplishmentId() {
		return accomplishmentId;
	}
	public void setAccomplishmentId(long accomplishmentId) {
		this.accomplishmentId = accomplishmentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Accomplishments [accomplishmentId=" + accomplishmentId + ", description=" + description + "]";
	}
	
	
	
}
