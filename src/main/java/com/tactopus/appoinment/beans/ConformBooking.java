package com.tactopus.appoinment.beans;

import org.springframework.data.mongodb.core.mapping.Document;

public class ConformBooking implements Comparable<ConformBooking>{
	
	  private String doctor;
	  private String speciality;
	  private String date;
	  private int slot;
	  private String name;
	  
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getSlot() {
		return slot;
	}
	public void setSlot(int slot) {
		this.slot = slot;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	  
	@Override
	public int compareTo(ConformBooking e) {
		return this.getDate().compareTo(e.getDate());
	}
	  
	  
	  

}
