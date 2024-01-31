package com.hexaware.careercrafterfinal.entities;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Component
@Entity
public class ReferenceLinks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long linkId;
	
	private String link;

	public ReferenceLinks() {
		super();
	}

	public ReferenceLinks(long linkId, String link) {
		super();
		this.linkId = linkId;
		this.link = link;
	}

	public long getLinkId() {
		return linkId;
	}

	public void setLinkId(long linkId) {
		this.linkId = linkId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "ReferenceLinks [linkId=" + linkId + ", link=" + link + "]";
	}
	
	
}
