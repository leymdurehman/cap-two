package com.techelevator.excelsior.model;

import java.util.List;

public interface VenueDAO {
	
	
	
	public List <Venue> getAllVenues();
	
	
	
	public Venue getVenueByCityID(long cityID);
	
	public String getCityNameFromCityID(long cityID);
	//join venue on city table city_id
	
	public String getCategoryFromVenueID(long VenueID);
	// join venue on category_venue with venue id then get categoryName
	

	// return string category name
	
	
	

}
