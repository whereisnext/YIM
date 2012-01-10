package com.vance.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"event"
})

@XmlRootElement(name="event")
public class Event {
    @XmlElement(name="event")
	private String event;
	
	@XmlAttribute
	private String start;
	
	@XmlAttribute
	private String end;
	
	@XmlAttribute
	private String text;
	
	@XmlAttribute
	private Boolean isDuration;
	
	@XmlAttribute
	private String title;
	
	
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getIsDuration() {
		return isDuration;
	}

	public void setIsDuration(Boolean isDuration) {
		this.isDuration = isDuration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
		
}
