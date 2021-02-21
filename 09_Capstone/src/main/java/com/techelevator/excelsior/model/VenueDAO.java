package com.techelevator.excelsior.model;

import java.util.ArrayList;
import java.util.List;

public interface VenueDAO {
	
	
	
	public List <Venue> getAllVenues();
	
	
	
	public Venue getVenueByCityID(long cityID);
	
	public String getCityNameFromCityID(long cityID);
	//join venue on city table city_id
	
	public List<String> getCategoryFromVenueID(long VenueID);
	// join venue on category_venue with venue id then get categoryName



	public List<Venue> returnVenueInfo();



	public ArrayList<Venue> returnVenueInfoById(long venueId);
	

	// return string category name
	
	
	

}
