package com.techelevator.excelsior.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.excelsior.model.Venue;
import com.techelevator.excelsior.model.VenueDAO;

public class JDBCVenueDAO implements VenueDAO{

	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCVenueDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	//returns all venue names
	@Override
	public List<Venue> getAllVenues() {
		
		String sqlGetVenues = "select venue.name as venue_name " + 
				"from venue " + 
				"join city on venue.city_id = city.id " + 
				"join state on city.state_abbreviation = state.abbreviation " + 
				"order by venue_name asc";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetVenues);
		
		Venue theVenue; //= new Venue();
		ArrayList<Venue> venueList = new ArrayList<>();
		
		while(results.next()) {
			theVenue = mapRowToVenue(results);
			venueList.add(theVenue);
		}
		return venueList;

	}
	
	
	//returns all venue + info organized alphabetically w/o categories
	@Override
	public List<Venue> returnVenueInfo() {
		
		String sqlGetVenues = "select venue.id as venue_id, venue.name as venue_name, city.name as city, city.state_abbreviation as state" + 
				"from venue " + 
				"join city on venue.city_id = city.id " + 
				"join state on city.state_abbreviation = state.abbreviation " + 
				"order by venue_name asc";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetVenues);
		
		Venue theVenue; //= new Venue();
		ArrayList<Venue> venueList = new ArrayList<>();
		
		while(results.next()) {
			theVenue = mapRowToVenue(results);
			venueList.add(theVenue);
		}
		return venueList;

	}

	
	//Venue info by entered id search (returns venue id, venue name, city, state)
	@Override
	public Venue returnVenueInfoById(long venueId) {
		
		String sqlGetVenues = "select venue.id as venue_id, venue.description, venue.name as venue_name, city.name as city, city.state_abbreviation as state" + 
				"from venue " + 
				"join city on venue.city_id = city.id " + 
				"join state on city.state_abbreviation = state.abbreviation " + 
				"where venue.id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetVenues, venueId);
		
		Venue theVenue = null; //= new Venue();
		//ArrayList<Venue> venueList = new ArrayList<>();
		
		while(results.next()) {
			theVenue = mapJoinRowToVenue(results);
			//venueList.add(theVenue);
		}
		return theVenue;
	}
	
	

	
	//List of Strings-- Categories per Venue returned as list
	@Override
	public List<String> getCategoryFromVenueID(long venueID) {
		
		String sqlCategories = "select category.name as category_name" + 
				"	from venue " + 
				"	join city on venue.city_id = city.id  " + 
				"	join state on city.state_abbreviation = state.abbreviation " + 
				"	join category_venue on category_venue.venue_id = venue.id " + 
				"	join category on category_venue.category_id = category.id " + 
				"	where venue.id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlCategories, venueID);
		
		Venue theVenue; //= new Venue();
		ArrayList<Venue> categoriesList = new ArrayList<>();
		ArrayList<String> categoryStringList = new ArrayList<>();
		
		while(results.next()) {
			theVenue = mapRowToVenue(results);
			categoriesList.add(theVenue);
			categoryStringList.add(theVenue.getCategory());
		}
		return categoryStringList;
		
	}
	
	
//	select category.name as category_name
//	from venue
//	join city on venue.city_id = city.id 
//	join state on city.state_abbreviation = state.abbreviation
//	join category_venue on category_venue.venue_id = venue.id
//	join category on category_venue.category_id = category.id
//	where venue.id = ?
	
	
	private Venue mapJoinRowToVenue(SqlRowSet results) {
		 
	Venue venueJoin = new Venue();
		 
		 venueJoin.setId(results.getLong("venue_id"));
		 venueJoin.setName(results.getString("venue_name"));
		 venueJoin.setCity("city");
		 venueJoin.setDescription(results.getString("description"));
		 venueJoin.setState("state");
		return venueJoin;
		
	}
	
	
	
	private Venue mapRowToVenue(SqlRowSet results) {
		Venue theVenue;
		theVenue = new Venue();
		theVenue.setId(results.getLong("venue_id"));
		theVenue.setName(results.getString("venue_name"));
//		theVenue.setCityID(results.getLong("city"));
		theVenue.setDescription(results.getString("description"));
		theVenue.setCategory(results.getString("category_name"));
		
		return theVenue;
	}

	
	// take in venue id put, 
	
	
	
	


	@Override
	public Venue getVenueByCityID(long cityID) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String getCityNameFromCityID(long cityID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
}
