package com.techelevator.excelsior.model;

import java.sql.Date;
import java.util.List;



public interface SpaceDAO {


	public List<Space> getAllSpacesforVenueID();

	
	public List<Space> getSpacesByClient(Date startDate, Date endDate);
	
	
	public String getMonth(String customerDate);
	
	
	
	public Date getEndDate(Date startDate, int numberNumberOfDays); 
	
	// adding num of days to start date
	
	
	// return SQL query result of end date for customer 
	
	
	
	
	
	
}
