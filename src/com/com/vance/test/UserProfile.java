package com.vance.test;
import java.util.List;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
//import net.javabeat.articles.java6.newfeatures.jaxb.*;

import com.vance.test.Position;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "firstName",
    "lastName",
    "summary",
    "positions"
})
@XmlRootElement(name = "person")
public class UserProfile {

    @XmlElement(name = "id")
    protected String id;
    @XmlElement(name = "first-name")
	private String firstName="N/A";
    @XmlElement(name="last-name")
    private String lastName="N/A";
    @XmlElement(name="summary")
	private com.vance.domain.summary summary;
    
    @XmlElement(name="positions")
    private Positions positions;
    
    
    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public com.vance.domain.summary getSummary() {
		return summary;
	}

	public void setSummary(com.vance.domain.summary summary) {
		this.summary = summary;
	}

	public Positions getPositions() {
		return positions;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
}
	