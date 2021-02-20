package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.excelsior.jdbc.JDBCReservationDAO;
import com.techelevator.excelsior.jdbc.JDBCSpaceDAO;
import com.techelevator.excelsior.jdbc.JDBCVenueDAO;
import com.techelevator.excelsior.model.Reservation;
import com.techelevator.excelsior.model.ReservationDAO;
import com.techelevator.excelsior.model.Space;
import com.techelevator.excelsior.model.SpaceDAO;
import com.techelevator.excelsior.model.Venue;
import com.techelevator.excelsior.model.VenueDAO;

public class ExcelsiorCLI {

	
	private Menu menu;
	private Venue venue;
	private Reservation reservation;
	private Space space;
	private VenueDAO venueDAO;
	private ReservationDAO reservationDAO;
	private SpaceDAO spaceDAO;
	
	
	
	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		ExcelsiorCLI application = new ExcelsiorCLI(dataSource);
		application.run();
	}

	public ExcelsiorCLI(DataSource datasource) {
		//this.menu = new Menu(System.in, System.out);
		venueDAO = new JDBCVenueDAO(datasource);
		spaceDAO = new JDBCSpaceDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);
	}

	public void run() {
		
		// menu . display menu
		int input = 1;
		while (input == 1) {
			
			// print all ids and names venueDAO.getAllVenues();
			long venueID = venue.getId();
			while( input == venueID) {
				Venue venueForCustomer = venueDAO.returnVenueInfoById(venueID);
				//method function
				venueForCustomer.getName();
				//venueForCustomer.getCity()
				//venueForCustomer.g
			//	println venue.
				
				
			}
		
			
			
			
			
			
			
		}
	
		
		
		
		
		
		
		
		
		
		
		
		
		

	}
}
