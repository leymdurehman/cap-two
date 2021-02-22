package com.techelevator;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
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
	private static final String MAIN_MENU_QUIT = "Q";
	private static final String RETURN_TO_PREVIOUS_MENU = "R";
	private static final String VIEW_SPACES = "1";
	private static final String LIST_VENUES = "1";
	private static final String RESERVE_A_SPACE = "1";
	//private stat
	
	
	public static void main(String[] args) throws ParseException {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		


		ExcelsiorCLI application = new ExcelsiorCLI(dataSource);
		application.run();
	}

	public ExcelsiorCLI(DataSource datasource) {
		this.menu = new Menu();
		venueDAO = new JDBCVenueDAO(datasource);
		spaceDAO = new JDBCSpaceDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);
		
	}

	public void run() throws ParseException {
			
	
		
		
	  while (true) {	
		String input = menu.showHomeMenu();
		//input.toUpperCase();
		Map<Integer, Venue> venueMap = new HashMap<Integer, Venue>();
		if (input.contentEquals(MAIN_MENU_QUIT)) {
		return; // exits program
		}	
		else if (input.contentEquals(LIST_VENUES)) {
			venueMap = menu.showVenueNamesMenu(venueDAO.getAllVenues()); //display all venues	
			String customerVenueChoice = menu.venueChoice(); 
				if (customerVenueChoice.contentEquals(RETURN_TO_PREVIOUS_MENU)) {
					menu.showHomeMenu();
					
			} else if(customerVenueChoice != "R") { 	
			int venueIDInput = Integer.parseInt(customerVenueChoice);
			menu.displayVenueDetails(venueMap.get(venueIDInput));
			long venueIDCustomer = venueMap.get(venueIDInput).getId();
			menu.printVenueCategories(venueDAO.getCategoryFromVenueID(venueIDCustomer));
			menu.showVenueDetails(venueMap.get(venueIDInput));
			String venueDetailschoice = menu.venueDetailsMenu();
			if (venueDetailschoice.contentEquals(VIEW_SPACES)) {
		List<Space> allSpacesFromVenue = spaceDAO.getAllSpacesforVenueID(venueIDCustomer);
			Venue venueInfo = venueMap.get(venueIDInput);
					menu.venueSpacesMenu(venueInfo,allSpacesFromVenue);
			} String spaceChoice = menu.spaceDetailsMenu();
				if(spaceChoice.contentEquals(RETURN_TO_PREVIOUS_MENU)) {
					menu.venueDetailsMenu();
					
				}
			if (spaceChoice.contentEquals(RESERVE_A_SPACE)) {
				
				String startDate = menu.spaceStartDate();
				int numOfDays = menu.lengthOfStay();
				int numOfPeople = menu.attendanceNumber();
				String endDate = reservationDAO.getEndDate(startDate, numOfDays);
				int startMonth = reservationDAO.getStartMonthNum(startDate);
				int endMonth = reservationDAO.getEndMonthNum(endDate);
				List<Space> availableSpaces= reservationDAO.getAvailableSpacesByByDateRange(startDate, endDate, 
															numOfPeople, startMonth, endMonth, venueIDCustomer);
				if (availableSpaces == null) {
						menu.noAvabilibity();
						menu.venueDetailsMenu();
				} else
						menu.spaceReservationMenu(availableSpaces, numOfDays);
				int spaceID = menu.userSpaceSelection();
				
					if (spaceID == 0) {
							menu.showHomeMenu();
							//break;
							
					} else {
							
					String reservationName = menu.userName();
					
						if (reservationName != null) {
		//					menu.userName();
		//				} else {
							
							
					reservationDAO.createReservation(spaceID, numOfPeople, startDate, endDate, reservationName);		
					
						String spaceName = spaceDAO.getSpaceNameByClient(spaceID);
						String venueName = venueMap.get(venueIDInput).getName();
						
						menu.userReservation(numOfPeople, reservationName, startDate, endDate, spaceName, venueName);
						
								}
					
						} 		
							
					}		
			
				}
				
			}							
							
	  	}						 
						
	}

}
