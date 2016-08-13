package com.alacriti.rentalbookportal.vo;

import javax.ws.rs.FormParam;

public class RegistrationBean {
	private @FormParam("newFirstName") String firstName;
	private @FormParam("newLastName") String lastName;
	private @FormParam("newEmail") String email;
	private @FormParam("newPhoneNumber") int phoneNumber;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
