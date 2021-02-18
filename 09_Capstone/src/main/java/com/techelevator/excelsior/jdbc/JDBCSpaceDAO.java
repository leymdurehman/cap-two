package com.techelevator.excelsior.jdbc;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
	public List<Space> getSpacesByClient(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		
		// check availabilites of a space by date
		return null;
	}

	
	@Override
	public String getMonth(String customerDate) {
		// DESIGN CHOICE - How do we want user to input data?
		String[] dateParts = {""};
		String month = dateParts[0];
		
		// TEST: Does this account for zeros?
		if (customerDate.contains("-")) {
			dateParts = customerDate.split("-");
		}
		if (customerDate.contains("/")){
			dateParts = customerDate.split("/");
		}
		
		return month;
	}

	
	@Override
	public Date getEndDate(Date startDate, int numberNumberOfDays) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		
		
		//https://attacomsian.com/blog/java-add-days-to-date
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DATE, numberNumberOfDays);
		
		
		// Below code is a String
		return formatter.format(c.getTime());
	}

	



}
