package com.techelevator.excelsior.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;



public interface SpaceDAO {


	public List<Space> getAllSpacesforVenueID(String userInputVenueId);
	// takes value that user passes in on the View Venues Menu
	
	public List<Space> getSpacesByClient(LocalDate startDate, LocalDate endDate);
	
	
	public String getMonth(String customerDate);
	
	
	public LocalDate getEndDate(LocalDate startDate, int numberNumberOfDays); 
	
	// adding num of days to start date
	
	
	// return SQL query result of end date for customer 
	
	
	
	
	
	
}
