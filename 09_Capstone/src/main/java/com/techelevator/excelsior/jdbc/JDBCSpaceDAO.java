package com.techelevator.excelsior.jdbc;

import java.sql.Date;
import java.util.List;

import com.techelevator.excelsior.model.Space;
import com.techelevator.excelsior.model.SpaceDAO;

public class JDBCSpaceDAO implements SpaceDAO{

	private Space space = new Space();
	
	
	
	
	@Override
	public List<Space> getAllSpacesforVenueID() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Space> getSpacesByClient(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String getMonth(String customerDate) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Date getEndDate(Date startDate, int numberNumberOfDays) {
		// TODO Auto-generated method stub
		return null;
	}

	



}
