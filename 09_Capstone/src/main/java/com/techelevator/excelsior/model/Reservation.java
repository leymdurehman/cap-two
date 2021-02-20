package com.techelevator.excelsior.model;

import java.sql.Date;

public class Reservation {

private long reservationID;
private long spaceID;
private int numberOfattendees;
private String startDate;
private String endDate;
private String reservedFor;

public long getReservationID() {
	return reservationID;
}
public void setReservationID(long reservationID) {
	this.reservationID = reservationID;
}
public long getSpaceID() {
	return spaceID;
}
public void setSpaceID(long spaceID) {
	this.spaceID = spaceID;
}
public int getNumberOfattendees() {
	return numberOfattendees;
}
public void setNumberOfattendees(int numberOfattendees) {
	this.numberOfattendees = numberOfattendees;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
public String getReservedFor() {
	return reservedFor;
}
public void setReservedFor(String reservedFor) {
	this.reservedFor = reservedFor;
}
	
	
	
	
	
	
	
	
}
