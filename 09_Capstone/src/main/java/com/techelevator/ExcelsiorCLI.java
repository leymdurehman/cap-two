package com.techelevator;

import java.util.Map;

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
		
		Venue venue = new Venue();
		Reservation reservation = new Reservation();
		Space space = new Space();
		Menu menu = new Menu();

		ExcelsiorCLI application = new ExcelsiorCLI(dataSource, menu);
		application.run();
	}

	public ExcelsiorCLI(DataSource datasource, Menu menu) {
		this.menu = new Menu();
		venueDAO = new JDBCVenueDAO(datasource);
		spaceDAO = new JDBCSpaceDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);
		
	}

	public void run() {
		//String input = "1";
		String input = menu.showHomeMenu();
		//menu.showVenueNamesMenu();
			
		//if ( input != "1" || input != "Q" || input != "123456789") {
			while (input != "Q") {
					
				Map<Integer,Venue> venueMap = menu.showVenueNamesMenu(venueDAO.getAllVenues());	
				String choice = menu.venueChoice();
				
				while(choice != "Q") {
					
					int inputToInt = Integer.parseInt(choice);
					// Venue venueChoice = venueDAO.returnVenueInfoById(inputToInt);
					//String userInput = menu.showVenueDetails(venueChoice);
					menu.displayVenueDetails(venueMap.get(inputToInt));
					menu.printVenueCategories(venueDAO.getCategoryFromVenueID(venueMap.get(inputToInt).getId()));
					menu.showVenueDetails(venueMap.get(inputToInt));
					
					if (input == "1") {
						String userSelectionViewSpaces = menu.venueDetailsMenu();
						
						
						
						// View Spaces
						// List venue Spaces
						// id, name, open / close months, daily rate, max occupancy
						
						//menu.printVenueSpaces();
						
						if (userSelectionViewSpaces == "1") {
							
							
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
		
			if (input == "Q") {
				System.exit(1);
			}
		
		}
		//System.out.println("Please enter valid response.");	
		
	}
	
//}
