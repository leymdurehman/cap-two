package com.techelevator.excelsior.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.techelevator.excelsior.model.Space;
import com.techelevator.excelsior.model.SpaceDAO;

public class JDBCSpaceDAO implements SpaceDAO{

	//private Space space = new Space();
	private JdbcTemplate jdbcTemplate;
	
	public JDBCSpaceDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Space> getAllSpacesforVenueID(long userInputVenueId) {
		String sql = "SELECT id, name, max_occupancy, open_from, open_to, venue_id, CAST(daily_rate AS varchar) FROM space WHERE venue_id = ? LIMIT 5";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userInputVenueId);
		
		List<Space> spacesInVenue = new ArrayList<Space>();
		
		while(rows.next()) {
			spacesInVenue.add(mapRowToAvailSpace(rows));
		}	
		return spacesInVenue;
	}
	
	@Override
	public String getSpaceNameByClient(long userSpace) {
		// check availability of a space by date
		
		String sql = "SELECT name FROM space WHERE id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userSpace);
		rows.next();
			String name = (mapRowToSpaceName(rows));
			// this method should map row to reservation??
		
		
		return name;
	}

	
		
	@Override
	public String getMonth(String customerDate) {
		// DESIGN CHOICE - How do we want user to input data?
		//List<String> dateParts = new ArrayList<String>();
		
		// TEST: Does this account for zeros at start of month?
		//if (customerDate.contains("-")) {
		String[]month = customerDate.split("-");		
		//}
//		if (customerDate.contains("/")){
//			dateParts = customerDate.split("/");
//		}
		
		// function to separate the parts of date and arrange in an array so that I can pick out
		// any part of newly created array
		// User will input date as string through menu scanner input as String 01-01-2010;
		
		// After split: 01012010
		
		//String month = dateParts[0];
		
		return month[0];
	}

	
	@Override
	public LocalDate getEndDate(LocalDate startDate, int numberNumberOfDays) {
		LocalDate endDate = startDate.plusDays(numberNumberOfDays);
		return endDate;
		
	}
	
	// mapRowToReservation???
	public Space mapRowToSpace(SqlRowSet row) {
		Space space = new Space();

		space.setDailyRate(row.getString("daily_rate"));
		// set daily rate may need to be changed to BigDecimal?
		space.setId(row.getInt("id"));
		space.setMaxOccupancy(row.getInt("max_occupancy"));
		space.setName(row.getString("name"));
		space.setOpenFrom(row.getInt("open_from"));
		space.setOpenTo(row.getInt("open_to"));
		space.setVenueID(row.getInt("venue_id"));
		
		return space;
	}

	public String mapRowToSpaceName(SqlRowSet result) {
		Space space = new Space();
		space.setName(result.getString("name"));
		String name = space.getName();
		return name;
		
	}
	
	
	
	
	
	
	
	
	public Space mapRowToAvailSpace(SqlRowSet row) {
		Space space = new Space();

		space.setDailyRate(row.getString("daily_rate"));
		// set daily rate may need to be changed to BigDecimal?
		space.setId(row.getInt("id"));
		space.setMaxOccupancy(row.getInt("max_occupancy"));
		space.setName(row.getString("name"));
		space.setOpenFrom(row.getInt("open_from"));
		space.setOpenTo(row.getInt("open_to"));
		space.setVenueID(row.getInt("venue_id"));
		
		return space;
	}
	
	
	
	public double getDailyRateint(String dailyRate) {


	    String newStr = dailyRate.replaceAll(",", "");
	  
	        if (newStr.contains("$")) {
	            System.out.println("true");
	        }

	        String newStr2 = newStr.replace("$", "");

	    double dailyRateInt = Double.parseDouble(newStr2);

	    return dailyRateInt;
	}

	@Override
	public List<Space> getAllSpacesforVenueID(int userInputVenueId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Space> getSpacesByClient(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEndDate(String startDate, int numOfDays) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Space> getSpacesByClient(long u) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	

}
