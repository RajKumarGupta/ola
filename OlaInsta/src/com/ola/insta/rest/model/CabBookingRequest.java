package com.ola.insta.rest.model;

public class CabBookingRequest {

	private String userId;
	private Double lat;
	private Double lon;
	private String address;
	private Long bookingTime; // Time in millis

	public Long getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Long bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
