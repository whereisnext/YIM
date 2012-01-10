package com.vance.test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//import com.google.code.linkedinapi.schema.Company;
//import com.google.code.linkedinapi.schema.StartDate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "title",
    "summary",
    "company"
})
@XmlRootElement(name = "position")
public class Position {
	@XmlElement(name="id")
	private int id;
	@XmlElement(name="title")
	private  String title;
	@XmlElement(name="summary")
	private String summary;
	@XmlElement(name="company")
	private Company company;
	
	
//	private StartDate startDate;
//	@XmlElement(name="is-aaa")
//	private boolean isCurrent;
	
//	private Company company;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

//	public StartDate getStartDate() {
//		return startDate;
//	}

//	public void setStartDate(StartDate startDate) {
//		this.startDate = startDate;
//	}

//	public boolean isCurrent() {
//		return isCurrent;
//	}
//
//	public void setCurrent(boolean isCurrent) {
//		this.isCurrent = isCurrent;
//	}

//	public Company getCompany() {
//		return company;
//	}

//	public void setCompany(Company company) {
//		this.company = company;
//	}
	
	
	
}
