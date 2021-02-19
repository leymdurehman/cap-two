package com.techelevator.excelsior.jdbc;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.excelsior.model.Space;
import com.techelevator.excelsior.model.SpaceDAO;

public class JDBCSpaceDAO implements SpaceDAO{

	private Space space = new Space();
	private JdbcTemplate jdbcTemplate;
	
	public JDBCSpaceDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	
	
	@Override
	public List<Space> getAllSpacesforVenueID(String userInputVenueId) {
		
		String sql = "SELECT * FROM space WHERE venue_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userInputVenueId);
		
		List<Space> spacesInVenue = new ArrayList<Space>();
		
		while(rows.next()) {
			// ???
			spacesInVenue.add((Space) rows);
		}	
		return spacesInVenue;
	}

	
	@Override
	public List<Space> getSpacesByClient(LocalDate startDate, LocalDate endDate) {
		// check availability of a space by date
		
		String sql = "SELECT * FROM reservation WHERE NOT (start_date BETWEEN ? AND ?) OR (end_date BETWEEN ? AND ?)";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, startDate, endDate, startDate, endDate);
		
		while(rows.next()) {
		// This needs to return available spaces.
		}
		
		return null;
	}

	
	@Override
	public String getMonth(String customerDate) {
		// DESIGN CHOICE - How do we want user to input data?
		String[] dateParts = {""};
		String month = dateParts[0];
		
		// TEST: Does this account for zeros at start of month?
		if (customerDate.contains("-")) {
			dateParts = customerDate.split("-");
		}
		if (customerDate.contains("/")){
			dateParts = customerDate.split("/");
		}
		
		return month;
	}

	
	@Override
	public LocalDate getEndDate(LocalDate startDate, int numberNumberOfDays) {
		
		LocalDate endDate = startDate.plusDays(numberNumberOfDays);
	
		return endDate;
		
	}
}
