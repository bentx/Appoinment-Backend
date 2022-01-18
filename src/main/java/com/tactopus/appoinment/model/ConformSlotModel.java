package com.tactopus.appoinment.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ConformSlot")
public class ConformSlotModel  implements Comparable<ConformSlotModel> {
	  
	

	  @Id
	  private String id;
	  private String doctor;
	  private String speciality;
	  private Date date;
	  private int slot;
	  private String name;
	  private String status;
	  
	  
	  
	  
	 public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
			return id;
		}
	public void setId(String id) {
			this.id = id;
		}
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
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	public int compareTo(ConformSlotModel e) {
		return this.getDate().compareTo(e.getDate());
	}
}
