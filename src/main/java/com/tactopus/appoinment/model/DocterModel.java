package com.tactopus.appoinment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="DoctorList")
public class DocterModel {

	@Id
	private int id;
	private String name;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "DoctorList [id=" + id + ", name=" + name + "]";
	}


}