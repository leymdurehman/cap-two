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
	public List<Space> getAllSpacesforVenueID(int userInputVenueId) {
		String sql = "SELECT * FROM space WHERE venue_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userInputVenueId);
		
		List<Space> spacesInVenue = new ArrayList<Space>();
		
		while(rows.next()) {
			spacesInVenue.add(mapRowToSpace(rows));
		}	
		return spacesInVenue;
	}
	
	@Override
	public List<Space> getSpacesByClient(LocalDate startDate, LocalDate endDate) {
		// check availability of a space by date
		
		String sql = "SELECT space_id FROM reservation WHERE NOT (start_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)) OR (end_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE))";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, startDate, endDate, startDate, endDate);
		
		List<Space> availableSpaces = new ArrayList<Space>();
		
		while(rows.next()) {
			availableSpaces.add(mapRowToSpace(rows));
		}
		
		return availableSpaces;
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
	
	public Space mapRowToSpace(SqlRowSet row) {
		Space space = new Space();
		space.setAccessible(row.getBoolean("is_accessible"));
		space.setDailyRate(row.getBigDecimal("daily_rate"));
		// set daily rate may need to be changed to BigDecimal?
		space.setId(row.getInt("id"));
		space.setMaxOccupancy(row.getInt("max_occupancy"));
		space.setName(row.getString("name"));
		space.setOpenFrom(row.getInt("open_from"));
		space.setOpenTo(row.getInt("open_to"));
		space.setVenueID(row.getInt("venue_id"));
		
		return space;
	}
}
