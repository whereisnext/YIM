package com.vance.test;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.vance.test.Position;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "position"
})
@XmlRootElement(name = "positions")
public class Positions {
	@XmlElement(name="position")
	private List<Position> position;

	public List<Position> getPosition() {
		return position;
	}

	public void setPosition(List<Position> postion) {
		this.position = postion;
	}

//	public void setPostion(Position postion) {
//		this.postion = postion;
//	}
}

