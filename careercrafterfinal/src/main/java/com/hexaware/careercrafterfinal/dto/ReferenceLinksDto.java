package com.hexaware.careercrafterfinal.dto;

public class ReferenceLinksDto {

	private long linkId;
	
	private String link;

	public ReferenceLinksDto() {
		super();
	}

	public ReferenceLinksDto(long linkId, String link) {
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
