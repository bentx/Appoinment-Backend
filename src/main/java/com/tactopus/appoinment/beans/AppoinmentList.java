package com.tactopus.appoinment.beans;

import java.util.List;

public class AppoinmentList  implements Comparable<AppoinmentList> {
	
	private String date;
	private List<ConformResponse> appoinments;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<ConformResponse> getAppoinments() {
		return appoinments;
	}
	public void setAppoinments(List<ConformResponse> appoinments) {
		this.appoinments = appoinments;
	} 

	@Override
	public int compareTo(AppoinmentList e) {
		return this.getDate().compareTo(e.getDate());
	}
	  
	  
	
}
