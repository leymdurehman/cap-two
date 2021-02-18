package com.techelevator.excelsior.model;

import java.sql.Date;

public class Reservation {

private long reservationID;
private long spaceID;
private int numberOfattendees;
private Date startDate;



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
public Date getStartDate() {
	return startDate;
}
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
	
	
	
	
	
	
	
	
}
