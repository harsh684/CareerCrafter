package com.hexaware.careercrafterfinal.entities;

import java.sql.Blob;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "ResumeDoc")
public class ResumeDoc {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String docId;

	private String name;

	private String type;

	@Lob
	private Blob data;

	public ResumeDoc() {
		super();
	}

	public ResumeDoc( String name, String type, Blob data) {
		super();
		this.name = name;
		this.type = type;
		this.data = data;
	}

	public String getdocId() {
		return docId;
	}

	public void setId(String docId) {
		this.docId = docId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Blob getData() {
		return data;
	}

	public void setData(Blob data) {
		this.data = data;
	}
	
}
