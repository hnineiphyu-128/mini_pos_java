package com.mmit.model.entity;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	private String city;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
