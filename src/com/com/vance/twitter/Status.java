package com.vance.twitter;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"created_at",
		"id",
		"text",
		"source",
		"truncated",
		"favorited",
		"in_reply_to_status_id",
		"in_reply_to_user_id",
		"in_reply_to_screen_name",
		"retweet_count",
		"retweeted",
		"user",
		"geo",
		"coordinates",
		"place",
		"contributors"
})

@XmlRootElement(name = "connections")
public class Status {
	@XmlElement(name="created_at")
	private String created_at;
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTruncated() {
		return truncated;
	}
	public void setTruncated(String truncated) {
		this.truncated = truncated;
	}
	public String getFavorited() {
		return favorited;
	}
	public void setFavorited(String favorited) {
		this.favorited = favorited;
	}
	public String getIn_reply_to_status_id() {
		return in_reply_to_status_id;
	}
	public void setIn_reply_to_status_id(String in_reply_to_status_id) {
		this.in_reply_to_status_id = in_reply_to_status_id;
	}
	public String getIn_reply_to_user_id() {
		return in_reply_to_user_id;
	}
	public void setIn_reply_to_user_id(String in_reply_to_user_id) {
		this.in_reply_to_user_id = in_reply_to_user_id;
	}
	public String getIn_reply_to_screen_name() {
		return in_reply_to_screen_name;
	}
	public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
		this.in_reply_to_screen_name = in_reply_to_screen_name;
	}
	public String getRetweet_count() {
		return retweet_count;
	}
	public void setRetweet_count(String retweet_count) {
		this.retweet_count = retweet_count;
	}
	public String getRetweeted() {
		return retweeted;
	}
	public void setRetweeted(String retweeted) {
		this.retweeted = retweeted;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getContributors() {
		return contributors;
	}
	public void setContributors(String contributors) {
		this.contributors = contributors;
	}
	@XmlElement(name="created_at")
	
	private int id;
	
	@XmlElement(name="text")
	private String text;
	@XmlElement(name="source")
	private String source;
	@XmlElement(name="truncated")
	private String truncated;
	@XmlElement(name="favorited")
	private String favorited;
	@XmlElement(name="in_reply_to_status_id")
	private String in_reply_to_status_id;
	@XmlElement(name="in_reply_to_user_id")
	private String in_reply_to_user_id;
	@XmlElement(name="in_reply_to_screen_name")
	private String in_reply_to_screen_name;
	@XmlElement(name="retweet_count")
	private String retweet_count;
	@XmlElement(name="retweeted")
	private String retweeted;
	@XmlElement(name="user")
	private String user;
	@XmlElement(name="geo")
	private String geo;
	@XmlElement(name="coordinates")
	private String coordinates;
	@XmlElement(name="place")
	private String place;
	@XmlElement(name="contributors")
	private String contributors;

}
