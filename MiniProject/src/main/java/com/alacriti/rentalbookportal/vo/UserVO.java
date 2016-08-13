package com.alacriti.rentalbookportal.vo;
public class UserVO {
	private long userId;
	private String firstName;
	private String lastName;
	private String emailId;
	private long phoneNumber;
	private long role;
	public UserVO()
	{
		
	}
	
	public UserVO(String firstName, String lastName, String emailId,
			long phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
	}
	public UserVO(String firstName,long role)
	{
		this.firstName=firstName;
		this.role=role;
	}
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public long getRole() {
		return role;
	}
	public void setRole(long role) {
		this.role = role;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
