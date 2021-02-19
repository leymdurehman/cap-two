package com.techelevator.excelsior.model;

import java.math.BigDecimal;

public class Space {

private long id;
private long venueID;
private String name;
private boolean isAccessible;
private int openFrom;
private int openTo;
private BigDecimal dailyRate = BigDecimal.ZERO;
private int maxOccupancy;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public long getVenueID() {
	return venueID;
}
public void setVenueID(long venueID) {
	this.venueID = venueID;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public boolean isAccessible() {
	return isAccessible;
}
public void setAccessible(boolean isAccessible) {
	this.isAccessible = isAccessible;
}
public int getOpenFrom() {
	return openFrom;
}
public void setOpenFrom(int openFrom) {
	this.openFrom = openFrom;
}
public int getOpenTo() {
	return openTo;
}
public void setOpenTo(int openTo) {
	this.openTo = openTo;
}
public double getDailyRate() {
	
//	  BigDecimal amount = dailyRate;
//    amount = amount.replace("$", "");
//    amount = amount.replace(",", "");
//    double intAmount= (int)Double.parseDouble(amount);
    
    long longAmount = dailyRate.longValue();
    
	return longAmount;
}



public void setDailyRate(BigDecimal dailyRate) {
	this.dailyRate = dailyRate;
}
public int getMaxOccupancy() {
	return maxOccupancy;
}
public void setMaxOccupancy(int maxOccupancy) {
	this.maxOccupancy = maxOccupancy;
}
	
	

	
	
}
