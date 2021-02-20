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
		String input = "0";
			
		if ( input != "1" || input != "Q" ) {
			while (input == "1") {
				
				
				menu.showVenueSpaces();
				
				
				// print all ids and names venueDAO.getAllVenues();
				// long venueID = venue.getId();
				
				while( input == "venueID") {
					// venue ID is a scanner input variable
					
					
					if (input == "1") {
						// View Spaces
						// List venue Spaces
						// id, name, open / close months, daily rate, max occupancy
						
						if (input == "1") {
							// Reserve a Space
							// printLn to user - When do you need the space
							// print ln to user how many days?
							// println to user how many people in attendance?
							
							// println to user the spaces that meet criteria
							
							// println to user which space would you like to reserve?
							// who is it for?
							
							// print out confirmation Number
							
							if (input == "0") {
								break;
							}
							
						if (input == "R") {
							// break to previous screen
							break;
						}
						}
						
					}
					if (input == "2") {
						// Search for reservation with user set input
					}
					else if (input == "R"){
						// return to previous screen
						break;
					}
					
					
					
				}
			
				if (input == "R") {
					//Return to previous screen
					break;
				}	
				
				
			}
		
			if (input == "R") {
				System.exit(1);
			}
		
		
		
		}
		
		System.exit(1);
		
		
		
		
		
		

	}
}
