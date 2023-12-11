package com.visitor.log.system.visitor;

public class Visitor {
	
	private String firstName, lastName, contactNumber, address, purposeOfVisit, guestID, dateTimeEntered, dateTimeExited;
	private int visitorCode;
	private boolean isCurrentlyInSchool;

	public String getPurposeOfVisit() {
		return purposeOfVisit;
	}
	public void setPurposeOfVisit(String purposeOfVisit) {
		this.purposeOfVisit = purposeOfVisit;
	}
	public String getGuestID() {
		return guestID;
	}
	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}
	public int getVisitorCode() {
		return visitorCode;
	}
	public void setVisitorCode(int visitorCode) {
		this.visitorCode = visitorCode;
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
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDateTimeEntered() {
		return dateTimeEntered;
	}
	public void setDateTimeEntered(String dateTimeEntered) {
		this.dateTimeEntered = dateTimeEntered;
	}
	public String getDateTimeExited() {
		return dateTimeExited;
	}
	public void setDateTimeExited(String dateTimeExited) {
		this.dateTimeExited = dateTimeExited;
	}
	public boolean isCurrentlyInSchool() {
		return isCurrentlyInSchool;
	}
	public void setCurrentlyInSchool(boolean isCurrentlyInSchool) {
		this.isCurrentlyInSchool = isCurrentlyInSchool;
	}
	
	

}
