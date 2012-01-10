package com.vance.domain;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"event"
})

@XmlRootElement(name="data")
public class TimeLine {
	@XmlElement(name="event")
	private ArrayList<Event> event=new ArrayList<Event>();
	
	public ArrayList<Event> getEvents() {
		return event;
	}
	public void setEvents(ArrayList<Event> events) {
		this.event = events;
	}
}
